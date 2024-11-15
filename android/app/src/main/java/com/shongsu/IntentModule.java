package com.shongsu.intent;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import java.util.HashMap;
import java.util.Map;

public class IntentModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public IntentModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return "IntentModule";
    }

    @ReactMethod
    public void getExtraString(String key, Promise promise) {
        try {
            Intent intent = getCurrentActivity().getIntent();
            String value = intent.getStringExtra(key);
            if (value != null) {
                promise.resolve(value);
            } else {
                promise.resolve(null); // Return null if the value is not found
            }
        } catch (Exception e) {
            promise.reject("ERROR", e);
        }
    }

    @ReactMethod
    public void getExtraBoolean(String key, Promise promise) {
        try {
            Intent intent = getCurrentActivity().getIntent();
            boolean value = intent.getBooleanExtra(key, false);
            promise.resolve(value);
        } catch (Exception e) {
            promise.reject("ERROR", e);
        }
    }

    @ReactMethod
    public void getExtraInt(String key, Promise promise) {
        try {
            Intent intent = getCurrentActivity().getIntent();
            int value = intent.getIntExtra(key, -1); // Default to -1 if not found
            promise.resolve(value);
        } catch (Exception e) {
            promise.reject("ERROR", e);
        }
    }
}
