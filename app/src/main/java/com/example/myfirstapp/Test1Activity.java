package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Test1Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTitleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        init();
    }

    /**
     *  初始化
     */
    protected void init() {
        mTitleTextView = findViewById(R.id.mTitleTextView);
        mTitleTextView.setText("test1");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.mTitleLeft:
                finish();
                break;
            case R.id.mTitleRight:
                Toast.makeText(this, "提交数据", Toast.LENGTH_SHORT).show();
            default: break;
        }
    }
}
