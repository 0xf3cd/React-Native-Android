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

// import android.app.ActivityManagerNative;
// import android.content.res.Configuration;
// import java.awt.GraphicsEnvironment;

import android.widget.TextView;  

import android.content.Context; 
import android.provider.Settings;
import android.text.TextUtils;

import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodInfo;

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
    public void getCurrentLanguage() {
        Locale locale = Locale.getDefault();
        String lang = locale.getLanguage() + "-" + locale.getCountry();
        Toast.makeText(getReactApplicationContext(), lang, Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void getAllLanguages(){
        Locale chinaLocale;
        Locale newLocale,testLocale;
        Locale locales[] = Locale.getAvailableLocales();
        String result = "";
        for(Locale locale:locales){
            //获取系统支持的语言和国家
            // System.out.println("Language："+locale.getLanguage()+"   Country:"+locale.getCountry());
            result += "Language：" + locale.getLanguage() + "   Country:" + locale.getCountry() + "\n";

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

        Toast.makeText(getReactApplicationContext(), result, Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");         
        String timeStr = formatter.format(System.currentTimeMillis());  

        Toast.makeText(getReactApplicationContext(), timeStr, Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void getAllFonts() { 
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

        Toast.makeText(getReactApplicationContext(), result, Toast.LENGTH_LONG).show();
    } 

    @ReactMethod
    public void getFontSize() {
        // Configuration mCurConfig = new Configuration();

        // try {
        //     mCurConfig.updateFrom(ActivityManagerNative.getDefault().getConfiguration());
        // } catch (RemoteException e) {
        //     // Log.w(TAG, "Unable to retrieve font size");
        // }
 
        // Log.w(TAG, "getFontSize(), Font size is " + mCurConfig.fontScale);
        TextView tv = new TextView(getReactApplicationContext());

        Toast.makeText(getReactApplicationContext(),"默认字体大小为："+tv.getTextSize()/(tv.getResources().getDisplayMetrics().density),Toast.LENGTH_LONG).show();
    }

    @ReactMethod
    public void getDefaultInputMethod() {
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
        Toast.makeText(getReactApplicationContext(), mDefaultInputMethodPkg, Toast.LENGTH_LONG).show();

    }

    @ReactMethod
    public void getAllInputMethods(){
        Context context = getReactApplicationContext();

        String result = "";
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> methodList = imm.getInputMethodList();
        for(InputMethodInfo mi : methodList ) {
            CharSequence name = mi.loadLabel(context.getPackageManager());
            // Log.d(TAG, "getList: +"+name+ mi.getId());
            result += name + mi.getId() + '\n';
        }

        Toast.makeText(getReactApplicationContext(), result, Toast.LENGTH_LONG).show();

    }
}