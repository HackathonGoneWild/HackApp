package com.andtinder.demo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.dd.CircularProgressButton;

/**
 * Created by tomer on 6/13/15.
 */
public class CustomDialogClass extends Dialog{

    public CustomDialogClass(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loader);
        final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.btnWithText);
        circularButton1.setIndeterminateProgressMode(true);
        circularButton1.setProgress(50);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);

    }
}