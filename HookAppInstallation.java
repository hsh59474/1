package io.github.ylimit.droidbotapp.hookapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;

import java.io.File;
import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static io.github.ylimit.droidbotapp.hookapp.Utils.copyFile;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class HookAppInstallation {
    //Optional. Hook the installation of app and extract the corresponding apk file for detecting Drive-by Download.
    public static void init(final XC_LoadPackage.LoadPackageParam lpparam){
        String path = "/sdcard/DriveByDownload";
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdir())
            {
                XposedBridge.log("No Permission to Mkdir for Storing APK Files");
                return;
            }
        }
        try {
            findAndHookMethod(
                    "android.app.Instrumentation",
                    lpparam.classLoader,
                    "execStartActivity",
                    Context.class,
                    IBinder.class,
                    IBinder.class,
                    Activity.class,
                    Intent.class,
                    int.class,
                    Bundle.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            Intent intent = (Intent) param.args[4];
                            Field f1 = intent.getClass().getDeclaredField("mType");
                            f1.setAccessible(true);
                            String cn = (String) f1.get(intent);
                            if (cn != null) {
                                if (cn.equals("application/vnd.android.package-archive")) {
                                    Field f3 = intent.getClass().getDeclaredField("mData");
                                    f3.setAccessible(true);
                                    Uri mData = (Uri)f3.get(intent);
                                    String mPath = mData.getPath();
                                    String[] aa = mData.toString().split("/");
                                    String filename = lpparam.packageName + "-" + aa[aa.length-1];
                                    String suffix = filename.substring(filename.length()-4,filename.length());
                                    if (!suffix.equals(".apk")){
                                        filename = filename + ".apk";
                                    }
                                    String filepath = "/sdcard/DriveByDownload/" + filename;
                                    copyFile(mPath,filepath);
                                }
                            }
                        }
                    });
        } catch (Error e){
        }

    }
}