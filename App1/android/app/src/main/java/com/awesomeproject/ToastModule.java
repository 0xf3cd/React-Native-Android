// ToastModule.java

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

public class ToastModule extends ReactContextBaseJavaModule {

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";

  public ToastModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "ToastExample";
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
    constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
    return constants;
  }

  @ReactMethod
  public void show(String message, int duration) {
    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }

  // @ReactMethod
  // public void measureLayout(
  //     int tag,
  //     int ancestorTag,
  //     Callback errorCallback,
  //     Callback successCallback) {
  //   try {
  //     measureLayout(tag, ancestorTag, mMeasureBuffer);
  //     float relativeX = PixelUtil.toDIPFromPixel(mMeasureBuffer[0]);
  //     float relativeY = PixelUtil.toDIPFromPixel(mMeasureBuffer[1]);
  //     float width = PixelUtil.toDIPFromPixel(mMeasureBuffer[2]);
  //     float height = PixelUtil.toDIPFromPixel(mMeasureBuffer[3]);
  //     successCallback.invoke(relativeX, relativeY, width, height);
  //   } catch (IllegalViewOperationException e) {
  //     errorCallback.invoke(e.getMessage());
  //   }
  // }

  @ReactMethod
    public void show2(String msg, int duration, Callback callback){
        Toast.makeText(getReactApplicationContext(), "Js调用显示原生传递的参数是:"+msg, duration).show();
        callback.invoke("RNToastModule 调用JS方法");
    }
}