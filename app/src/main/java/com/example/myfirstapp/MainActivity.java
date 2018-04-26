package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private  TextView mTitleTextView;
    private Button mTitleLeft;
    private Button mTitleRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init() {
        mTitleTextView = (TextView) findViewById(R.id.mTitleTextView);
        mTitleLeft  = (Button) findViewById(R.id.mTitleLeft);
        mTitleRight  = (Button) findViewById(R.id.mTitleRight);
        mTitleTextView.setText("首页");
        mTitleLeft.setVisibility(View.INVISIBLE);
        mTitleRight.setVisibility(View.INVISIBLE);
    }

    /**
     * send button
     * @param view
     */
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

    public void openOne(View view) {
        Intent intent = new Intent(this, Test1Activity.class);
        startActivity(intent);
    }
    public void openTwo(View view) {
        Intent intent = new Intent(this, Test2Activity.class);
        startActivity(intent);
    }
    public void openThree(View view) {
        Intent intent = new Intent(this, Test3Activity.class);
        startActivity(intent);
    }
}
