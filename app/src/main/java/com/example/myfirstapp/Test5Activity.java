package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test5Activity extends AppCompatActivity implements View.OnClickListener,ListView.OnItemClickListener{
    private TextView mTitleTextView;
    private Dialog dialog;
    private String[] names = new String[]{"黑翼之巢", "熔火之心", "永恒之井", "冰冠堡垒"};
    private String[] says = new String[]{"黑翼之巢25人团队副本", "熔火之心25人团队副本", "永恒之井25人团队副本", "冰冠堡垒25人团队副本"};
    private int[] imgIds = new int[]{R.mipmap.list_item_img1, R.mipmap.list_item_img1, R.mipmap.list_item_img1, R.mipmap.list_item_img1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);
        init();
    }

    private void init() {
        dialog = new Dialog();
        mTitleTextView = (TextView)findViewById(R.id.mTitleTextView);
        mTitleTextView.setText("Test5");

        List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showItem = new HashMap<String, Object>();
            showItem.put("icon", imgIds[i]);
            showItem.put("name", names[i]);
            showItem.put("says", says[i]);
            listItem.add(showItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), listItem, R.layout.list_item_icon, new String[]{"icon", "name", "says"}, new int[]{R.id.imgIcon, R.id.name, R.id.says});
        ListView listView = (ListView)findViewById(R.id.list_item);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.mTitleLeft:
                finish();
                break;
            default: break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
        dialog.showToast(this, "您点击了第" + (index + 1) + "项");
    }
}
