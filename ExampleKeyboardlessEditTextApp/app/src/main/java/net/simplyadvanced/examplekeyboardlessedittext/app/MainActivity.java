package net.simplyadvanced.examplekeyboardlessedittext.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import net.simplyadvanced.widgets.KeyboardlessEditText;

/** Simple sample app to demonstrate the KeyboardlessEditText */
public class MainActivity extends Activity implements View.OnClickListener {

    private EditText mEditText;
    private View mDot;
    private View mDash;
    private View mBackspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.inputText);

        mDot = findViewById(R.id.dotButton);
        mDash = findViewById(R.id.dashButton);
        mBackspace = findViewById(R.id.backspaceButton);

        mDot.setOnClickListener(this);
        mDash.setOnClickListener(this);
        mBackspace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dotButton:
                pressKey(KeyEvent.KEYCODE_PERIOD);
                return;
            case R.id.dashButton:
                pressKey(KeyEvent.KEYCODE_MINUS);
                return;
            case R.id.backspaceButton:
                pressKey(KeyEvent.KEYCODE_DEL);
                return;
        }
    }

    private void pressKey(int keycode) {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keycode);
        mEditText.onKeyDown(keycode, event);
    }

}
