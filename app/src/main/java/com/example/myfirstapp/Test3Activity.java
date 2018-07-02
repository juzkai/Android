package com.example.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.ToggleButton;

public class Test3Activity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    private Dialog dialog = new Dialog();
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private ToggleButton toggleButton;
    private Switch aSwitch;
    private TitleBarActivity mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        mTitle = (TitleBarActivity)findViewById(R.id.title_bar);
        mTitle.setOnRightBtnClick(new TitleBarActivity.onRightBtnClick() {
            @Override
            public void onRightBtnClick() {
                postData();
            }
        });
        init();
    }
    /**
     *  初始化
     */
    protected void init() {
        checkBox1 = (CheckBox)findViewById(R.id.banana);
        checkBox2 = (CheckBox)findViewById(R.id.apple);
        checkBox3 = (CheckBox)findViewById(R.id.orange);

        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);

        toggleButton = (ToggleButton)findViewById(R.id.tbnView);
        aSwitch = (Switch)findViewById(R.id.switchView);

        toggleButton.setOnCheckedChangeListener(this);
        aSwitch.setOnCheckedChangeListener(this);

        // RadioButton设置选中时间监听器
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)findViewById(i);
                CharSequence str = radioButton.getText();
                dialog.showToast(getApplicationContext(), str.toString());
            }
        });
    }
    protected void postData() {
        // 通过点击其他按钮 获取 RadioButton选中的值
        String choose = "你选择了";
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                CharSequence str = radioButton.getText();
                choose += " --- " + str;
                break;
            }
        }
        if(checkBox1.isChecked()) choose += " --- " + checkBox1.getText().toString();
        if(checkBox2.isChecked()) choose += " --- " + checkBox2.getText().toString();
        if(checkBox3.isChecked()) choose += " --- " + checkBox3.getText().toString();

        dialog.showToast(this, choose);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.mTitleRight:
                postData();
            default: break;
        }
    }

    /**
     * 多选框选中
     * @param compoundButton
     * @param b
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id = compoundButton.getId();
        if(id == R.id.tbnView) {
            if(compoundButton.isChecked()) {
                dialog.showToast(this, "打开开关");
                aSwitch.setChecked(true);
            } else {
                dialog.showToast(this, "关闭开关");
                aSwitch.setChecked(false);
            }
        } else if(id == R.id.switchView) {
            if(compoundButton.isChecked()) {
                dialog.showToast(this, "打开开关ON");
                toggleButton.setChecked(true);
            } else {
                dialog.showToast(this, "关闭开关OFF");
                toggleButton.setChecked(false);
            }
        } else {
            if(compoundButton.isChecked()) {
                dialog.showToast(this, compoundButton.getText().toString());
            }
        }
    }
}
