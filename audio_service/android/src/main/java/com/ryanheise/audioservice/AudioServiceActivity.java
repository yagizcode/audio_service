package com.ryanheise.audioservice;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class AudioServiceActivity extends FlutterActivity {
    private static final String MICCHANNEL = "com.example.micService";

    @Override
    public FlutterEngine provideFlutterEngine(@NonNull Context context) {
        return AudioServicePlugin.getFlutterEngine(context);
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), MICCHANNEL).setMethodCallHandler(
            (call, result) -> {
                if (call.method.equals("startMic")) {
                    Intent intent = new Intent(this, MicService.class);
                    startService(intent);
                    result.success(null);
                } else if (call.method.equals("stopMic")) {
                    Intent intent = new Intent(this, MicService.class);
                    stopService(intent);
                    result.success(null);
                } else {
                    result.notImplemented();
                }
            }
        );
    }
}
