import 'package:flutter/material.dart';

/// This is a simple test show widget location, which works fine for pure flutter
/// project, however, when using this package in native project, the location
/// is different
class Location extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _LocationState();
}

class _LocationState extends State<Location> {
  Offset _offset = Offset.zero;
  GlobalKey _globalKey = GlobalKey();

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((timeStamp) {
      _getSizeAndPosition();
    });
  }

  void _getSizeAndPosition() {
    RenderBox renderBox = _globalKey.currentContext.findRenderObject();
    Offset textOffset = renderBox.localToGlobal(Offset.zero);
    print('_getSizeAndPosition, textOffset = $textOffset');
    setState(() {
      _offset = textOffset;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Container(
          color: Colors.white,
        ),
        Column(
          children: [
            Container(
              margin: EdgeInsets.all(10),
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(16.0),
              ),
              height: 410,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Expanded(
                    flex: 1,
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          Text(
                            'Today',
                            style: TextStyle(fontSize: 20),
                            textAlign: TextAlign.center,
                          ),
                          SizedBox(
                            height: 5,
                          ),
                          Stack(alignment: Alignment.center, children: [
                            FlutterLogo(
                              key: _globalKey,
                              size: 40,
                            ),
                            Text(
                              '0',
                              style: TextStyle(
                                color: Color(0xFFFF7032),
                                fontWeight: FontWeight.w600,
                                fontSize: 18,
                              ),
                            )
                          ])
                        ]),
                  ),
                  Expanded(
                      flex: 2,
                      child: Text(
                        'Second Flex',
                        style: TextStyle(fontSize: 20),
                      )),
                  Expanded(
                      flex: 1,
                      child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              "Today add up",
                              style: TextStyle(fontSize: 20),
                              textAlign: TextAlign.center,
                            ),
                            SizedBox(
                              height: 10,
                            ),
                            Text.rich(TextSpan(children: <TextSpan>[
                              TextSpan(
                                  text: '10',
                                  style: TextStyle(
                                      color: Colors.blue,
                                      fontWeight: FontWeight.bold,
                                      fontSize: 22)),
                              TextSpan(
                                  text: 'min',
                                  style: TextStyle(
                                      fontSize: 18,
                                      fontWeight: FontWeight.w600))
                            ]))
                          ])),
                ],
              ),
            ),
            Text(
              'The offset of _globalKey is $_offset',
              style: TextStyle(fontSize: 25),
            )
          ],
        )
      ],
    );
  }
}
