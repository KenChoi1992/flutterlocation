import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutterlocation/test_location.dart';

void main() {
  runApp(MyApp());

  SystemUiOverlayStyle systemUiOverlayStyle = SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
      statusBarIconBrightness: Brightness.dark);
  SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Location Test',
        theme: ThemeData(
            primaryColor: Colors.white,
            scaffoldBackgroundColor: Colors.white,
            textTheme: TextTheme(bodyText1: TextStyle(color: Colors.black))),
        home: Scaffold(
          backgroundColor: Colors.white,
          appBar: AppBar(
            centerTitle: true,
            title: Text(
              'Test location for ADD TO APP',
              style: TextStyle(fontSize: 18),
              textAlign: TextAlign.center,
            ),
          ),
          body: Location(),
        ));
  }
}
