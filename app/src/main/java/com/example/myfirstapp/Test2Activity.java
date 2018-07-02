package com.example.myfirstapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Test2Activity extends AppCompatActivity implements View.OnClickListener{
    private Dialog dialog;
    private Integer num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        dialog = new Dialog();
        // 在 onCreate中恢复数据的一定要先判断 savedInstanceState不为null
//        if (savedInstanceState != null) {
//            num = savedInstanceState.getInt("num");
//            if(num != null) {
//                dialog.showToast(Test2Activity.this, num.toString());
//            }
//        }

    }

    /**
     * 用户按下返回键、或者调用了finish()方法销毁activity，则onSaveInstanceState不会被调用
     * onSaveInstanceState在以下情况下会被调用
     * 1、当用户按下HOME键时
     * 2、从最近应用中选择运行其他的程序时
     * 3、按下电源按键（关闭屏幕显示）时
     * 4、从当前activity启动一个新的activity
     * 5、屏幕方向切换时（横竖屏切换均会被调用）
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("num", 123);
    }

    /**
     * onRestoreInstanceState 在activity确实是被系统回收，重新创建activity情况下会被调用
     * onRestoreInstanceState被调用了则 onSaveInstanceState 必然被调用过
     * onRestoreInstanceState 的参数一定不会为空 用来做数据恢复比较方便
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        num = savedInstanceState.getInt("num");
        dialog.showToast(Test2Activity.this, "onRestoreInstanceState---" + num.toString());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent;
        Uri uri;
        switch (id) {
            case R.id.telCall: // 拨打电话
                uri = Uri.parse("tel:18262283481");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.actionView: // 打开浏览器
                uri= Uri.parse("https://wwww.baidu.com/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.googleMap:
                uri = Uri.parse("geo:117.1,36.6");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            default: break;
        }
    }
}
