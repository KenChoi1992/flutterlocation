import UIKit
import Flutter

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)
    let controller: FlutterViewController = window?.rootViewController as! FlutterViewController;
    let methodChannel = FlutterMethodChannel.init(name: "cn.lianqinba.strings/flutter", binaryMessenger: controller.binaryMessenger);
    methodChannel.setMethodCallHandler({
        (call: FlutterMethodCall, result: FlutterResult) -> Void in
        // Handle battery messages.
        if (call.method == "getUserApiParams") {
            
        }
      });
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
}
