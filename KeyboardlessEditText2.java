/*
The MIT License (MIT)

Copyright (c) 2014 Danial Goodwin (danialgoodwin.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package net.simplyadvanced.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This is the same as a native EditText, except that no soft keyboard
 * will appear when user clicks on widget. All other normal operations
 * still work.
 *
 * To use in XML, add a widget for <my.package.name>.KeyboardlessEditText
 * To use in Java, use one of the three constructors in this class
 */
public class KeyboardlessEditText2 extends EditText {

    private static final Method mShowSoftInputOnFocus = getMethod(
            EditText.class, "setShowSoftInputOnFocus", boolean.class);

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            setCursorVisible(true);
        }
    };

    private OnLongClickListener mOnLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            setCursorVisible(true);
            return false;
        }
    };

    public KeyboardlessEditText2(Context context) {
        super(context);
        initialize();
    }

    public KeyboardlessEditText2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public KeyboardlessEditText2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {
        synchronized (this) {
            setInputType(getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            setFocusableInTouchMode(true);
        }

        // Needed to show cursor when user interacts with EditText so that the edit operations
        // still work. Without the cursor, the edit operations won't appear.
        setOnClickListener(mOnClickListener);
        setOnLongClickListener(mOnLongClickListener);

//      setShowSoftInputOnFocus(false); // This is a hidden method in TextView.
        reflexSetShowSoftInputOnFocus(false); // Workaround.
        
        // Ensure that cursor is at the end of the input box when initialized. Without this, the
        // cursor may be at index 0 when there is text added via layout XML.
        setSelection(getText().length());
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        hideKeyboard();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final boolean ret = super.onTouchEvent(event);
        // Must be done after super.onTouchEvent()
        hideKeyboard();
        return ret;
    }

    private void hideKeyboard() {
        final InputMethodManager imm = ((InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE));
        if (imm != null && imm.isActive(this)) {
            imm.hideSoftInputFromWindow(getApplicationWindowToken(), 0);
        }
    }

    private void reflexSetShowSoftInputOnFocus(boolean show) {
        if (mShowSoftInputOnFocus != null) {
            invokeMethod(mShowSoftInputOnFocus, this, show);
        } else {
            // Use fallback method. Not tested.
            hideKeyboard();
        }
    }

    /**
     * Returns method if available in class or superclass (recursively),
     * otherwise returns null.
     */
    public static Method getMethod(Class<?> cls, String methodName, Class<?>... parametersType) {
        Class<?> sCls = cls.getSuperclass();
        while (sCls != Object.class) {
            try {
                return sCls.getDeclaredMethod(methodName, parametersType);
            } catch (NoSuchMethodException e) {
                // Just super it again
            }
            sCls = sCls.getSuperclass();
        }
        return null;
//        throw new RuntimeException("Method not found " + methodName);
    }

    /** Returns results if available, otherwise returns null. */
    public static Object invokeMethod(Method method, Object receiver, Object... args) {
        try {
            return method.invoke(receiver, args);
        } catch (IllegalArgumentException e) {
            Log.e("Safe invoke fail", "Invalid args", e);
        } catch (IllegalAccessException e) {
            Log.e("Safe invoke fail", "Invalid access", e);
        } catch (InvocationTargetException e) {
            Log.e("Safe invoke fail", "Invalid target", e);
        }

        return null;
    }

}
