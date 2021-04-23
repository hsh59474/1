package io.github.ylimit.droidbotapp.hookapp;

import android.os.Message;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

class HookDisableImmersive {
//To disable notifications of immersive mode.(Classname and params may differ in different API level, change accordingly.)
    static void init(final XC_LoadPackage.LoadPackageParam lpparam){
        try {
            XposedHelpers.findAndHookMethod(
                    "com.android.internal.policy.impl.ImmersiveModeConfirmation.H",//For 4.4
//                    "com.android.server.policy.ImmersiveModeConfirmation.H",//For 6.0
                    lpparam.classLoader,
                    "handleMessage",
                    Message.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param)
                                throws Throwable {
                            Message m = (Message) param.args[0];
                            m.what = 1;
                            param.args[0] = m;
                        }
                    });
        }catch (Error e){
        }
    }
}
