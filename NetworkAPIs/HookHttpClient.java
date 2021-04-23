package io.github.ylimit.droidbotapp.hookapp.NetworkAPIs;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static io.github.ylimit.droidbotapp.hookapp.Utils.getOutput;
import static io.github.ylimit.droidbotapp.hookapp.Utils.getStack;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class HookHttpClient {
    public static void init(final XC_LoadPackage.LoadPackageParam lpparam){
        try {
            findAndHookMethod(
                    "org.apache.http.impl.client.AbstractHttpClient",
                    lpparam.classLoader,
                    "execute",
                    HttpUriRequest.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            if (!param.args[0].getClass().getCanonicalName().contains("HttpPost") && !param.args[0].getClass().getCanonicalName().contains("HttpGet")) {
                                return;
                            }

                            Throwable ex = new Throwable();
                            String[] stacks = getStack(ex);

                            if (param.args[0].getClass().getCanonicalName().contains("HttpGet")) {
                                HttpGet httpget = (HttpGet) param.args[0];
                                String Url = httpget.getURI().toString();
                                getOutput(lpparam.packageName, Url, "HttpClient-Get", stacks);
                            }

                            if (param.args[0].getClass().getCanonicalName().contains("HttpPost")) {
                                HttpPost httppost = (HttpPost) param.args[0];
                                String Url = httppost.getURI().toString();
                                getOutput(lpparam.packageName, Url, "HttpClient-Post", stacks);
                            }
                        }
                    });
        } catch (Error e){
        }
    }
}