package com.example.myfirstapp;

import android.app.AlertDialog;
import android.content.Context;

public class AlertMsg extends AlertDialog{

    protected AlertMsg(Context context) {
        super(context);
    }

    protected AlertMsg(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected AlertMsg(Context context, int themeResId) {
        super(context, themeResId);
    }
}