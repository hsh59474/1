package io.github.ylimit.droidbotapp.hookapp.NetworkAPIs;

import java.net.URL;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static io.github.ylimit.droidbotapp.hookapp.Utils.getOutput;
import static io.github.ylimit.droidbotapp.hookapp.Utils.getStack;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

public class HookOkHttp {
    public static void init(final XC_LoadPackage.LoadPackageParam lpparam){
        try {
            findAndHookMethod("okhttp3.Request.Builder", lpparam.classLoader, "url", String.class,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "okhttp3-StringURL", stacks);
                }
            });
        } catch (Error e){
        }

        try {
            final Class<?> httpurl=findClass("okhttp3.HttpUrl",lpparam.classLoader);
            findAndHookMethod("okhttp3.Request.Builder", lpparam.classLoader, "url", httpurl,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "okhttp3-httpurl", stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("okhttp3.Request.Builder", lpparam.classLoader, "url", URL.class,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "okhttp3-URL", stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("com.android.okhttp.OkHttpClient",lpparam.classLoader,"open", URL.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "OkHttpClient", stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("com.squareup.okhttp.OkHttpClient",lpparam.classLoader,"open", URL.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "OkHttpClient", stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("okhttp.Request.Builder", lpparam.classLoader, "url", String.class,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "okhttp", stacks);
                }
            });
        } catch (Error e){
        }

    }
}