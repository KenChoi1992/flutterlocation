package com.cyg.addtoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        // !!! register method channel cause flutter location calculate deviation!
        registerFlutterMethodChannel()

        findViewById<Button>(R.id.toFlutterBtn).setOnClickListener { view ->
            val intent = FlutterActivity
                .withCachedEngine(FlutterBridge.FLUTTER_ENGINE_ID)
                .build(this)
            startActivity(intent)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    private var flutterEngine: FlutterEngine? = null
    private fun registerFlutterMethodChannel() {
        if (flutterEngine == null) {
            flutterEngine = FlutterEngine(this)
            flutterEngine!!.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
            FlutterEngineCache.getInstance().put(FlutterBridge.FLUTTER_ENGINE_ID, flutterEngine)
            val methodChannel =
                MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, FlutterBridge.FLUTTER_CHANNEL_ID)
            methodChannel.setMethodCallHandler(FlutterBridge.getInstance())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}