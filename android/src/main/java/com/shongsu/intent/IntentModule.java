package com.shongsu.intent;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.os.PowerManager;
import android.os.Build;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import com.facebook.react.uimanager.IllegalViewOperationException;

public class IntentModule extends ReactContextBaseJavaModule {

  public IntentModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "IntentModule";
  }

  @ReactMethod
  public void alert(String message) {
    Toast.makeText(getReactApplicationContext(), message, Toast.LENGTH_LONG).show();
  }

    @ReactMethod
    public void getBooleanExtra(String key, Promise promise) {
        ReactActivity activity = (ReactActivity) getCurrentActivity();
        if (activity != null) {
            Intent intent = activity.getIntent();
            if (intent != null && intent.hasExtra(key)) {
                promise.resolve(intent.getBooleanExtra(key, false)); // Change type as needed
            } else {
                promise.resolve(null);
            }
        } else {
            promise.reject("Activity doesn't exist");
        }
    }
}
