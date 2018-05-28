package com.example.myfirstapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Dialog {
    private Toast toast = null;
    private AlertDialog alertDialog = null;
    private AlertDialog.Builder builder = null;

    /**
     * toast框
     * @param context
     * @param str
     */
    public void showToast(Context context, String str) {

        TextView textView = (TextView) new TextView(context);
        textView.setBackgroundResource(R.drawable.toast_track); // 背景颜色
        textView.setText(str); // 内容
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite)); // 字体颜色
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16); // 字体大小
        textView.setPadding(50,30,50,30); // 内边距
        textView.setAlpha((float) 0.80); // 透明度
        // 控制 toast只展示最后一条
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 200);
        }
        toast.setView(textView);
        toast.show();
    }

}
