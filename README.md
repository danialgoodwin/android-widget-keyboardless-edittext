KeyboardlessEditText
====================

A native Android EditText, except that no keyboard will appear when user interacts with it. All other edit operations still work.

Users will still have the ability to select, cut, copy, and paste using this widget.

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
        

No keyboard!
![KeyboardlessEditText with no keyboard!](https://github.com/danialgoodwin/android-widget-keyboardless-edittext/raw/master/Screenshots/keyboardless-edittext-no-keyboard.png)


Edit mode!
![KeyboardlessEditText with no keyboard!](https://github.com/danialgoodwin/android-widget-keyboardless-edittext/raw/master/Screenshots/keyboardless-edittext-in-edit-mode.png)


