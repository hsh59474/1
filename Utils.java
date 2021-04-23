package io.github.ylimit.droidbotapp.hookapp;

import android.app.AndroidAppHelper;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    static public String packageName = "io.github.ylimit.droidbotapp";
    static public String className = "io.github.ylimit.droidbotapp.CommunicationService";

    //Exclude pre_installed apps when hooking, copied from "adb shell pm list package"
    public static final String PRE_INSTALLED = "package:android\n" +
            "package:com.andrew.apollo\n" +
            "package:com.android.apps.tag\n" +
            "package:com.android.backupconfirm\n" +
            "package:com.android.bluetooth\n" +
            "package:com.android.browser\n" +
            "package:com.android.calculator2\n" +
            "package:com.android.calendar\n" +
            "package:com.android.camera\n" +
            "package:com.android.camera2\n" +
            "package:com.android.cellbroadcastreceiver\n" +
            "package:com.android.certinstaller\n" +
            "package:com.android.contacts\n" +
            "package:com.android.customlocale2\n" +
            "package:com.android.defcontainer\n" +
            "package:com.android.deskclock\n" +
            "package:com.android.development\n" +
            "package:com.android.development_settings\n" +
            "package:com.android.dialer\n" +
            "package:com.android.documentsui\n" +
            "package:com.android.dreams.basic\n" +
            "package:com.android.dreams.phototable\n" +
            "package:com.android.email\n" +
            "package:com.android.exchange\n" +
            "package:com.android.externalstorage\n" +
            "package:com.android.galaxy4\n" +
            "package:com.android.gallery3d\n" +
            "package:com.android.gesture.builder\n" +
            "package:com.android.htmlviewer\n" +
            "package:com.android.incallui\n" +
            "package:com.android.inputdevices\n" +
            "package:com.android.inputmethod.latin\n" +
            "package:com.android.keychain\n" +
            "package:com.android.keyguard\n" +
            "package:com.android.launcher\n" +
            "package:com.android.location.fused\n" +
            "package:com.android.magicsmoke\n" +
            "package:com.android.mms\n" +
            "package:com.android.music\n" +
            "package:com.android.musicfx\n" +
            "package:com.android.musicvis\n" +
            "package:com.android.nfc\n" +
            "package:com.android.noisefield\n" +
            "package:com.android.onetimeinitializer\n" +
            "package:com.android.packageinstaller\n" +
            "package:com.android.pacprocessor\n" +
            "package:com.android.phasebeam\n" +
            "package:com.android.phone\n" +
            "package:com.android.printspooler\n" +
            "package:com.android.providers.calendar\n" +
            "package:com.android.providers.contacts\n" +
            "package:com.android.providers.downloads\n" +
            "package:com.android.providers.downloads.ui\n" +
            "package:com.android.providers.media\n" +
            "package:com.android.providers.settings\n" +
            "package:com.android.providers.telephony\n" +
            "package:com.android.providers.userdictionary\n" +
            "package:com.android.proxyhandler\n" +
            "package:com.android.quicksearchbox\n" +
            "package:com.android.settings\n" +
            "package:com.android.sharedstoragebackup\n" +
            "package:com.android.shell\n" +
            "package:com.android.smspush\n" +
            "package:com.android.soundrecorder\n" +
            "package:com.android.stk\n" +
            "package:com.android.systemui\n" +
            "package:com.android.vending\n" +
            "package:com.android.videoeditor\n" +
            "package:com.android.voicedialer\n" +
            "package:com.android.vpndialogs\n" +
            "package:com.android.wallpaper\n" +
            "package:com.android.wallpaper.holospiral\n" +
            "package:com.android.wallpaper.livepicker\n" +
            "package:com.android.wallpapercropper\n" +
            "package:com.bel.android.dspmanager\n" +
            "package:com.cyanogenmod.account\n" +
            "package:com.cyanogenmod.filemanager\n" +
            "package:com.cyanogenmod.lockclock\n" +
            "package:com.cyanogenmod.trebuchet\n" +
            "package:com.cyanogenmod.updater\n" +
            "package:com.cyanogenmod.wallpapers\n" +
            "package:com.example.android.apis\n" +
            "package:com.example.android.livecubes\n" +
            "package:com.genymotion.genyd\n" +
            "package:com.genymotion.superuser\n" +
            "package:com.genymotion.systempatcher\n" +
            "package:com.google.android.backuptransport\n" +
            "package:com.google.android.feedback\n" +
            "package:com.google.android.gms\n" +
            "package:com.google.android.gsf\n" +
            "package:com.google.android.gsf.login\n" +
            "package:com.google.android.onetimeinitializer\n" +
            "package:com.google.android.partnersetup\n" +
            "package:com.google.android.play.games\n" +
            "package:com.google.android.setupwizard\n" +
            "package:com.google.android.syncadapters.calendar\n" +
            "package:com.google.android.syncadapters.contacts\n" +
            "package:com.google.language\n" +
            "package:com.google.system.sensor\n" +
            "package:com.lge.SprintHiddenMenu\n" +
            "package:com.lge.update\n" +
            "package:com.qualcomm.qcrilmsgtunnel\n" +
            "package:com.qualcomm.shutdownlistner\n" +
            "package:com.qualcomm.timeservice\n" +
            "package:com.redbend.vdmc\n" +
            "package:com.speedsoftware.rootexplorer\n" +
            "package:com.svox.pico\n" +
            "package:de.robv.android.xposed.installer\n" +
            "package:eu.chainfire.supersu\n" +
            "package:io.github.ylimit.droidbotapp\n" +
            "package:jackpal.androidterm\n" +
            "package:jp.co.omronsoft.openwnn\n" +
            "package:net.cactii.flash2\n" +
            "package:org.codeaurora.bluetooth\n" +
            "package:org.cyanogenmod.bugreport\n" +
            "package:org.cyanogenmod.launcher.home\n" +
            "package:org.cyanogenmod.theme.chooser\n" +
            "package:org.cyanogenmod.themes.provider\n" +
            "package:org.cyanogenmod.voiceplus\n" +
            "package:org.cyanogenmod.wallpapers.photophase\n" +
            "package:org.whispersystems.whisperpush\n" +
            "package:com.lr.keyguarddisabler\n";

    public static String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("MMdd-HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    public static String[] getStack(Throwable ex){
        StackTraceElement[] stackElements = ex.getStackTrace();
        String classnm = "";
        String[] stacks = {};
        if (stackElements.length > 1) {
            stacks = new String[stackElements.length-2];
            for (int i = 2; i < stackElements.length; i++) {
                classnm = stackElements[i].getClassName() + ':' + stackElements[i].getMethodName();
                stacks[i-2] = classnm;
            }
        }
        return stacks;
    }

    public static void getOutput(String pkg, String Url, String methodname, String[] stacks){
        String Output = "#" + "\n" + "#Pkg=" + pkg + "\n" + "#Url="+ Url + "\n" + "#Time=" + getTime() + "\n" + "#Method=" + methodname + "\n" ;
        for (int i = 0; i < stacks.length; i++) {
            Output = Output + "#Stack=" + stacks[i] + "\n";
        }
        String Out = Output + "#\n";
        writeToPC(Out);
    }

    public static void writeToPC(String strLine){
        Intent intent = new Intent();

        intent.setComponent(new ComponentName(packageName,className));
        intent.putExtra("DATA", strLine);

        Log.i("Xposed","Sending...." + strLine) ;
        Context context = (Context) AndroidAppHelper.currentApplication();
        context.startService(intent);
    }

    public static void copyFile(String fromFile, String toFile) throws IOException {
        FileInputStream ins = new FileInputStream(new File(fromFile));
        FileOutputStream out = new FileOutputStream(new File(toFile));
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, n);
        }

        ins.close();
        out.close();
    }
}
