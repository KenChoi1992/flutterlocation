package com.cyg.addtoapp

import android.util.Log
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/**
 * Flutter and Native call bridge
 */
class FlutterBridge private constructor() : MethodChannel.MethodCallHandler {

    companion object {
        const val FLUTTER_ENGINE_ID = "flutterEngine"
        const val FLUTTER_CHANNEL_ID = "com.cyg.addtoapp/flutter"

        const val FLUTTER_METHOD_LOGOUT = "logout"

        private val sInstance = FlutterBridge()
        fun getInstance(): FlutterBridge {
            return sInstance
        }
    }

    var methodChannel: MethodChannel? = null

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Log.e("test", "onMethodCall, method = ${call.method}")
        if (call.method == "callNative") {
            result.success("Hello from native")
        } else {
            result.notImplemented()
        }
    }

    fun invokeFlutterMethod(methodName: String, argument: Any?, channelResult: MethodChannel.Result?) {
        if (methodChannel == null) {
            val flutterEngine = FlutterEngineCache.getInstance().get(FLUTTER_ENGINE_ID)
            if (flutterEngine != null) {
                methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, FLUTTER_CHANNEL_ID)
            }
        }
        Log.i("test", "invoke flutter method: $methodName, method channel = $methodChannel")
        methodChannel?.invokeMethod(methodName, argument, channelResult)
    }

    fun release() {
        methodChannel = null
    }
}