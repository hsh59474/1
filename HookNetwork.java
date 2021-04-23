package io.github.ylimit.droidbotapp.hookapp;

import io.github.ylimit.droidbotapp.hookapp.NetworkAPIs.HookHttpClient;
import io.github.ylimit.droidbotapp.hookapp.NetworkAPIs.HookOkHttp;
import io.github.ylimit.droidbotapp.hookapp.NetworkAPIs.HookWebView;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

class HookNetwork {
    static void init(final XC_LoadPackage.LoadPackageParam lpparam){
        HookHttpClient.init(lpparam);
        HookOkHttp.init(lpparam);
        HookWebView.init(lpparam);
    }
}