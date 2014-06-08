KeyboardlessEditText
====================

A native Android EditText, except that no keyboard will appear when user interacts with it. All other edit operations still work.

Users will still have the ability to select, cut, copy, and paste using this widget. This was created because there were no other good/modular solutions in StackOverflow nor on GitHub. Each "answer" had a drawback that wouldn't have fit with my application. Example apps that use this are custom dialers, calculators, and any other apps where the soft keyboard would get in the way.

Included in this repo:
- An easy-to-use stand-alone ~/KeyboardlessEditText2.java that you can just copy into your project. (Has no dependencies)
- Another version ~/app/src/main/java/net/simplyadvanced/widgets/KeyboardlessEditText.java that depends on ~/.../net/simplyadvanced/utils/ReflectionUtils.java.
- A sample app that demonstrates the use of the keyboardless EditText, including input buttons and backspace.

To use KeyboardlessEditText:
- In XML, add a widget for `<my.package.name>.KeyboardlessEditText`

```
    <net.simplyadvanced.widgets.KeyboardlessEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/hello_world" />
```
            
- In Java, use one of the three constructors in the class

````
        EditText et = new KeyboardlessEditText(this);
````

## Test ##

To test the app, you can either install the APK in the root of this repo or go to: [https://play.google.com/store/apps/details?id=net.simplyadvanced.simplytonegenerator](https://play.google.com/store/apps/details?id=net.simplyadvanced.simplytonegenerator)


## Screenshots ##

No keyboard!
![KeyboardlessEditText with no keyboard!](https://github.com/danialgoodwin/android-widget-keyboardless-edittext/raw/master/Screenshots/keyboardless-edittext-no-keyboard.png)


Edit mode!
![KeyboardlessEditText with no keyboard!](https://github.com/danialgoodwin/android-widget-keyboardless-edittext/raw/master/Screenshots/keyboardless-edittext-in-edit-mode.png)
