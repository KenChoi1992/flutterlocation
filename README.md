# flutterlocation
A simple flutter demo shows get widget location bug.

Generally, we can use GlobalKey and RenderBox.localToGlobal API to get location:

```dart
RenderBox renderBox = _globalKey.currentContext.findRenderObject();
Offset textOffset = renderBox.localToGlobal(Offset.zero);
```

Every thing works just fine EXCEPT you're not using flutter MethodChannel!

By adding flutter MethodChannel, the offset calculated by above API is different!

## Project Explain

- The `pureflutter` folder is clean flutter demo, show the RIGHT location of a widget.

Clone this project and 
```shell script
cd flutterlocation
flutter pub get
cd pureflutter
flutter pub get
```
then
```shell script
flutter run
```
to run pure flutter demo.


- The `addtoapp` folder is an Android project which using the top package(flutterlocation) and
 
register a MethodChannel. You can using Android Studio open `addtoapp` to run this demo.

```shell script
cd flutter_module
flutter pub get
```
Then sync project and you can run `addtoapp` now.


