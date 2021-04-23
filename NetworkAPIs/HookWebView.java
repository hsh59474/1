package io.github.ylimit.droidbotapp.hookapp.NetworkAPIs;

import android.webkit.WebView;

import java.net.Proxy;
import java.net.URL;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static io.github.ylimit.droidbotapp.hookapp.Utils.getOutput;
import static io.github.ylimit.droidbotapp.hookapp.Utils.getStack;
import static io.github.ylimit.droidbotapp.hookapp.Utils.getTime;
import static io.github.ylimit.droidbotapp.hookapp.Utils.writeToPC;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class HookWebView {
    public static void init(final XC_LoadPackage.LoadPackageParam lpparam){
        try {
            findAndHookMethod("android.webkit.WebView", lpparam.classLoader, "loadUrl", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    WebView webview = (WebView) param.thisObject;
                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "WebView.loadUrl-"+webview, stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("android.webkit.WebView", lpparam.classLoader, "postUrl", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    WebView webview = (WebView) param.thisObject;
                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "WebView.postUrl-"+webview, stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("android.webkit.WebView", lpparam.classLoader, "loadDataWithBaseURL", String.class, String.class, String.class, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);
                    WebView webview = (WebView) param.thisObject;
                    String Url = "";
                    if (param.args[0] != null){
                        Url = param.args[0].toString();
                    }
                    getOutput(lpparam.packageName, Url, "WebView.postUrl-"+webview, stacks);

                    String Data = param.args[1].toString().replace("\n","").replace("\r","");

                    String Output = "#" + "\n" + "#Pkg=" + lpparam.packageName + "\n" + "#Time=" + getTime() + "\n" + "#Method=" + "WebView.loadDataWithBaseURL-"+webview + "\n" ;

                    if (param.args[0] != null){
                        Url = param.args[0].toString();
                        Output = Output + "#Url=" + Url + "\n";
                    }
                    Output = Output + "#Data="+Data + "\n";
                    if (param.args[2] != null){
                        String mimeType = param.args[2].toString();
                        Output = Output + "#mimeType="+mimeType + "\n";
                    }
                    if (param.args[4] != null){
                        String HistoryUrl = param.args[4].toString();
                        Output = Output + "#HistoryUrl="+HistoryUrl + "\n";
                    }
                    for (int i = 0; i < stacks.length; i++) {
                        Output = Output + "#Stack=" + stacks[i] + "\n";
                    }
                    String Out = Output + "#\n";
                    writeToPC(Out);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("android.webkit.WebViewClient", lpparam.classLoader, "shouldInterceptRequest", WebView.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    WebView webview = (WebView) param.args[0];
                    String Url=param.args[1].toString();
                    getOutput(lpparam.packageName, Url, "WebViewClient.shouldInterceptRequest-"+webview, stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("android.webkit.WebViewClient", lpparam.classLoader, "shouldOverrideUrlLoading", WebView.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    WebView webview = (WebView) param.args[0];
                    String Url=param.args[1].toString();
                    getOutput(lpparam.packageName, Url, "WebViewClient.shouldOverrideUrlLoading-"+webview, stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("com.android.org.chromium.android_webview.AwContents", lpparam.classLoader, "loadUrl", String.class,  new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    Throwable ex = new Throwable();
                    String[] stacks = getStack(ex);

                    String Url=param.args[0].toString();
                    getOutput(lpparam.packageName, Url, "AwContents.loadUrl", stacks);
                }
            });
        } catch (Error e){
        }

        try {
            findAndHookMethod("java.net.URLConnection", ClassLoader.getSystemClassLoader(), "openConnection", URL.class, Proxy.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);

                            Throwable ex = new Throwable();
                            String[] stacks = getStack(ex);

                            URL url = (URL) param.args[0];;
                            String Url=url.toString();
                            getOutput(lpparam.packageName, Url, "URL.openConnection", stacks);
                        }
                    });
        } catch (Error e){
        }

    }
}