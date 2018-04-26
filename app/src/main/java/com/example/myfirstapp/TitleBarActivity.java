package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TitleBarActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViews();
    }

    private void setUpViews() {
        super.setContentView(R.layout.activity_title_bar);
        mTitleTextView = findViewById(R.id.mTitleTextView);
        mTitleTextView.setText("");
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "点击返回", Toast.LENGTH_SHORT).show();
    }
}
