package com.example;

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class YourMainActivity: FlutterActivity() {

    companion object {
        const val FLUTTER_ENGINE_ID = "qinFlutterEngine"
        const val FLUTTER_CHANNEL_ID = "cn.lianqinba.strings/flutter"
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        registerFlutterMethodChannel()
    }

    private fun registerFlutterMethodChannel() {
        var flutterEngine = flutterEngine
        if (flutterEngine == null) {
            flutterEngine = FlutterEngine(this)
        }
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_ID, flutterEngine)
        val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, FLUTTER_CHANNEL_ID)
        methodChannel.setMethodCallHandler(FlutterBridge.getInstance())
    }

}
