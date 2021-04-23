package io.github.ylimit.droidbotapp.hookapp;

import android.app.Application;
import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Main implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        final String pkg = lpparam.packageName;

        HookDisableImmersive.init(lpparam);

        if (!Utils.PRE_INSTALLED.contains("package:"+pkg+"\n")) {//Hook new apps only

            // Solution for Multidex support from https://github.com/rovo89/xposedbridge/issues/30#issuecomment-68488797
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                HookNetwork.init(lpparam);
                HookAppInstallation.init(lpparam);//(Optional) For detecting Drive-by Download. To avoid permission issues, make dir '/sdcard/DriveByDownload/' on Android devices before using
            }
            });
        }
    }
}
