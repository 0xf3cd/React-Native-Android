package com.sysinfo;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import java.io.File;

import java.util.Locale;

import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date;  
import java.util.GregorianCalendar;  

import android.widget.TextView;  

import android.content.Context; 
import android.provider.Settings;
import android.text.TextUtils;

import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodInfo;

import android.net.ConnectivityManager; 
import android.net.NetworkInfo; 
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.lang.reflect.*;

public class SysInfo extends ReactContextBaseJavaModule {
    public SysInfo(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "SysInfo";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    // @ReactMethod
    // public void getAllMemory(Callback callback){
    //     callback.invoke(ri.getTotalRomSize());
    // }

    @ReactMethod
    public void show(String message) {
        Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void getCurrentLanguage(Callback callback) {
        Locale locale = Locale.getDefault();
        String lang = locale.getLanguage() + "-" + locale.getCountry();
        // Toast.makeText(getReactApplicationContext(), lang, Toast.LENGTH_LONG).show();
        callback.invoke(lang);
    }

    @ReactMethod
    public void getAllLanguages(Callback callback){
        Locale chinaLocale;
        Locale newLocale,testLocale;
        Locale locales[] = Locale.getAvailableLocales();
        String result = "";
        for(Locale locale:locales){
            //获取系统支持的语言和国家
            // System.out.println("Language："+locale.getLanguage()+"   Country:"+locale.getCountry());
            if(locale.getCountry() != "") {
                // result += "Language：" + locale.getLanguage() + "   Country:" + locale.getCountry() + "\n";
                result += locale.getLanguage() + "-" + locale.getCountry() + "\n";
            }

            //预览该国家语言
            // newLocale = new Locale(locale.getLanguage(),locale.getCountry());
            // Locale.setDefault(newLocale);
            // testLocale = Locale.getDefault();
            // System.out.println("     Display:"+testLocale.getDisplayLanguage()+"   "+testLocale.getDisplayCountry());
            // result += "     Display:"+testLocale.getDisplayLanguage()+"   "+testLocale.getDisplayCountry() + "\n";

            //用中文表示该国家语言
            // Locale.setDefault(new Locale("zh","CN"));
            // chinaLocale = Locale.getDefault();
            // System.out.println("     Display:"+testLocale.getDisplayLanguage()+"   "+testLocale.getDisplayCountry());
            // result += "     Display:"+testLocale.getDisplayLanguage()+"   "+testLocale.getDisplayCountry() + "\n";
        }

        // Toast.makeText(getReactApplicationContext(), result, Toast.LENGTH_LONG).show();
        callback.invoke(result);
    }

    @ReactMethod
    public void getCurrentTime(Callback callback) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");         
        String timeStr = formatter.format(System.currentTimeMillis());  

        // Toast.makeText(getReactApplicationContext(), timeStr, Toast.LENGTH_LONG).show();
        callback.invoke(timeStr);
    }

    @ReactMethod
    public void getAllFonts(Callback callback) { 
        String result = "";

        ArrayList<String> fontNames = new ArrayList<String>();
        File temp = new File("/system/fonts/");
        String fontSuffix = ".ttf";

        for(File font : temp.listFiles()) {
            String fontName = font.getName();
            if(fontName.endsWith(fontSuffix)) {
                fontNames.add(fontName.subSequence(0,fontName.lastIndexOf(fontSuffix)).toString());
                result += fontName.subSequence(0,fontName.lastIndexOf(fontSuffix)).toString() + '\n';
            }
        }

        // Toast.makeText(getReactApplicationContext(), result, Toast.LENGTH_LONG).show();
        callback.invoke(result);
    } 

    @ReactMethod
    public void getFontSize(Callback callback) {
        TextView tv = new TextView(getReactApplicationContext());
        Float size = tv.getTextSize()/(tv.getResources().getDisplayMetrics().density);

        // Toast.makeText(getReactApplicationContext(),"默认字体大小为："+,Toast.LENGTH_LONG).show();
        callback.invoke(size);
    }

    @ReactMethod
    public void getDefaultInputMethod(Callback callback) {
        Context context = getReactApplicationContext();
        String mDefaultInputMethodPkg = null;

        String mDefaultInputMethodCls = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.DEFAULT_INPUT_METHOD);
        //输入法类名信息
        // Log.d(TAG, "mDefaultInputMethodCls=" + mDefaultInputMethodCls);
        if (!TextUtils.isEmpty(mDefaultInputMethodCls)) {
            //输入法包名
            mDefaultInputMethodPkg = mDefaultInputMethodCls.split("/")[0];
            // Log.d(TAG, "mDefaultInputMethodPkg=" + mDefaultInputMethodPkg);
        }

        // Toast.makeText(getReactApplicationContext(), mDefaultInputMethodPkg, Toast.LENGTH_LONG).show();
        callback.invoke(mDefaultInputMethodPkg);
    }

    @ReactMethod
    public void getAllInputMethods(Callback callback){
        Context context = getReactApplicationContext();

        String result = "";
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> methodList = imm.getInputMethodList();
        for(InputMethodInfo mi : methodList ) {
            CharSequence name = mi.loadLabel(context.getPackageManager());
            // Log.d(TAG, "getList: +"+name+ mi.getId());
            result += name + mi.getId() + '\n';
        }

        // Toast.makeText(getReactApplicationContext(), result, Toast.LENGTH_LONG).show();
        callback.invoke(result);
    }

    public boolean isNetworkConnected(Context context) { 
        if (context != null) { 
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
            if (mNetworkInfo != null) { 
                return mNetworkInfo.isAvailable(); 
            } 
        } 
        return false; 
    }

    public boolean isWifiConnected(Context context) { 
        if (context != null) { 
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
            if (mWiFiNetworkInfo != null) { 
                return mWiFiNetworkInfo.isAvailable(); 
            } 
        } 
        return false; 
    }

    public boolean isMobileConnected(Context context) { 
        if (context != null) { 
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
            NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE); 
            if (mMobileNetworkInfo != null) { 
                return mMobileNetworkInfo.isAvailable(); 
            } 
        } 
        return false; 
    }
    
    public static int getConnectedType(Context context) { 
        if (context != null) { 
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) { 
                return mNetworkInfo.getType(); 
            } 
        } 
        return -1; 
    }

    public void toggleWiFi(Context context, boolean enabled) {
		WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		wm.setWifiEnabled(enabled);
    }
    
    // public void toggleMobileData(Context context, boolean enabled) {  
    //     ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
    //     Class<?> conMgrClass = null; // ConnectivityManager类  
    //     Field iConMgrField = null; // ConnectivityManager类中的字段  
    //     Object iConMgr = null; // IConnectivityManager类的引用  
    //     Class<?> iConMgrClass = null; // IConnectivityManager类  
    //     Method setMobileDataEnabledMethod = null; // setMobileDataEnabled方法  
    //     try {   
    //         // 取得ConnectivityManager类   
    //     conMgrClass = Class.forName(conMgr.getClass().getName());   
    //     // 取得ConnectivityManager类中的对象mService   
    //     iConMgrField = conMgrClass.getDeclaredField("mService");   
    //     // 设置mService可访问  
    //         iConMgrField.setAccessible(true);   
    //     // 取得mService的实例化类IConnectivityManager   
    //     iConMgr = iConMgrField.get(conMgr);   
    //     // 取得IConnectivityManager类   
    //     iConMgrClass = Class.forName(iConMgr.getClass().getName());   
    //     // 取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法   
    //     setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);   
    //     // 设置setMobileDataEnabled方法可访问   
    //     setMobileDataEnabledMethod.setAccessible(true);   
    //     // 调用setMobileDataEnabled方法   
    //     setMobileDataEnabledMethod.invoke(iConMgr, enabled);  
    //     } catch (ClassNotFoundException e) {   
    //         e.printStackTrace();  
    //     } catch (NoSuchFieldException e) {   
    //         e.printStackTrace();  
    //     } catch (SecurityException e) {   
    //         e.printStackTrace();  
    //     } catch (NoSuchMethodException e) {   
    //         e.printStackTrace();  
    //     } catch (IllegalArgumentException e) {   
    //         e.printStackTrace();  
    //     } catch (IllegalAccessException e) {   
    //         e.printStackTrace();  
    //     } catch (InvocationTargetException e) {   
    //         e.printStackTrace();  
    //     } 
    // }

    // public static void setDataEnabled(Context context, boolean enabled) {
    //     // try {
    //     //     TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    //     //     Method setMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
    //     //     if (null != setMobileDataEnabledMethod) {
    //     //         setMobileDataEnabledMethod.invoke(tm, enabled);
    //     //     }
    //     // } catch (Exception e) {
    //     //     e.printStackTrace();
    //     // }
    //     TelephonyManager teleManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    //     Class[] getArgArray = null;
    //     Class[] setArgArray = new Class[] {boolean.class};
    //     Object[] getArgInvoke = null;
    //     try {
    //         Method mGetMethod = teleManager.getClass().getMethod("getDataEnabled", getArgArray);
    //         Method mSetMethod = teleManager.getClass().getMethod("setDataEnabled", setArgArray);
    //         boolean isOpen = (Boolean) mGetMethod.invoke(teleManager, getArgInvoke);
    //         if (isOpen) {
    //             mSetMethod.invoke(teleManager, false);
    //         } else {
    //             mSetMethod.invoke(teleManager, true);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    @ReactMethod
    public void getConnectionType(Callback callback){
        Context context = getReactApplicationContext();
        int result = this.getConnectedType(context);
        String connectionType = "No Connection";
        if(result == -1) {
            connectionType = "No Connection";
        } else if(result == 0) {
            connectionType = "Using 3G/4G";
        } else if(result == 1) {
            connectionType = "Using Wi-Fi";
        }

        // Toast.makeText(context, String.valueOf(result), Toast.LENGTH_LONG).show();

        // this.setDataEnabled(getReactApplicationContext(), false);        
        callback.invoke(connectionType);
    }

    @ReactMethod
    public void turnOnWifi4G(Callback callback) {
        Context context = getReactApplicationContext();
        int result = this.getConnectedType(context);
        String returnInfo;

        if(result == -1) {
            toggleWiFi(context, true);
            returnInfo = "Wi-Fi 已打开\n由于系统权限问题，3G/4G 需要手动打开";
        } else {
            returnInfo = "网络已连接，不需要打开 Wi-Fi 或 3G/4G";
        }

        callback.invoke(returnInfo);
    }
} 