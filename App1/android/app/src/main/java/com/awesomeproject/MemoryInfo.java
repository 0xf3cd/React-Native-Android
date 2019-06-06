package com.awesomeproject;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

import com.awesomeproject.ROMInfo;

public class MemoryInfo extends ReactContextBaseJavaModule {
    ROMInfo ri = new ROMInfo();

    public MemoryInfo(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "MemoryInfo";
    }

//     private static final String DURATION_SHORT_KEY = "SHORT";
//     private static final String DURATION_LONG_KEY = "LONG";
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        // constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        // constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void getAllMemory(Callback callback){
        callback.invoke(ri.getTotalRomSize());
    }

    @ReactMethod
    public void getUsedMemory(Callback callback){
        callback.invoke(ri.getUsedRomSize());
    }

    @ReactMethod
    public void getAvailableMemory(Callback callback){
        callback.invoke(ri.getAvailableRomSize());
    }
}