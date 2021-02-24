package com.example;

import android.util.Log
//import cn.lianqinba.strings.MainApplication
//import cn.lianqinba.strings.data.AppDataManager
//import cn.lianqinba.strings.data.DataManager
//import cn.lianqinba.strings.util.AppURL
//import cn.lianqinba.strings.util.CommonUtils
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject

/**
 * Flutter and Native call bridge
 */
class FlutterBridge private constructor() : MethodChannel.MethodCallHandler {

    companion object {
        const val MOCK_DOMAIN_URL = "https://api.lianinstruments.com:14144/client/v2"
        const val MOCK_USER_UUID = "b85e4580-9587-4c22-942c-fe7a667a25d3"
        const val MOCK_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX3V1aWQiOiJiODVlNDU4MC05NTg3LTRjMjItOTQyYy1mZTdhNjY3YTI1ZDMiLCJkZXZpY2VfdXVpZCI6IjNlNWFlMDY1LTI4NGMtMzkyMi1iMzM3LWQ1ZGU0OGM0MGRiZSIsInBsYXRmb3JtIjoiYW5kcm9pZCIsImlhdCI6MTYxMTcxMDg2NCwiZXhwIjoxNjE2ODk0ODY0fQ.PZxl9cVmmnEPnIrlZvgiXg9gi2VFCrImAeaBZXvudh8"
        const val MOCK_LANGUAGE = "zh_cn"
        const val MOCK_DEVICE_INFO = "{\"device_uuid\":\"9c3fac5a-e426-37d4-96f8-16b9061760c7\",\"device_name\":\"V1824BA\",\"system_name\":\"Android\",\"system_version\":\"10\",\"app_version\":\"5.4.0\",\"app_network_type\":\"wifi\",\"app_bundle_id\":\"cn.lianqinba.strings\",\"lang\":\"zh_cn\",\"user_lang\":\"zh_cn\",\"country\":\"CN\",\"time_zone\":\"Asia\\/Shanghai\",\"width_pixels\":1080,\"height_pixels\":2267,\"brand\":\"vivo\",\"product\":\"PD1824\",\"channel\":\"debug\",\" domain_url\":\"https:\\/\\/api.lianinstruments.com:14144\",\" app_version_number\":2}}}"
        private val sInstance = FlutterBridge()
        fun getInstance(): FlutterBridge {
            return sInstance
        }
    }

//    val applicationContext: Context = MainApplication.getAppContext()
//    val dataManager: DataManager = MainApplication.get(applicationContext).component.dataManager

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Log.e("test", "onMethodCall, method = ${call.method}")
        if (call.method == "getUserApiParams") {
//            val currentUser = dataManager.currentUser
//            if (currentUser != null) {
                val userApiParams = JSONObject().apply {
                    put("domain_url", MOCK_DOMAIN_URL)
                    put("user_uuid", MOCK_USER_UUID)
                    put("http_params", JSONObject().apply {
                        put("token", MOCK_TOKEN)
                        put("language", MOCK_LANGUAGE)
                        put("device_info", MOCK_DEVICE_INFO)
                    })
                }
                result.success(userApiParams.toString())
//            } else {
//                result.error("ERROR", "User not login", null)
//            }
        } else if (call.method == "onBackPress") {
            Log.e("test", "receive onBackPress in native");
        } else {
            result.notImplemented()
        }
    }
}