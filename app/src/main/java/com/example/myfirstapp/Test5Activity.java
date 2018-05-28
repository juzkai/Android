package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfirstapp.base.MyAdapter;
import com.example.myfirstapp.entity.App;

import java.util.ArrayList;
import java.util.List;

public class Test5Activity extends AppCompatActivity implements View.OnClickListener,ListView.OnItemClickListener{
    private Dialog dialog;
    private String[] names = new String[]{"黑翼之巢", "熔火之心", "永恒之井", "冰冠堡垒"};
    private String[] says = new String[]{"黑翼之巢25人团队副本", "熔火之心25人团队副本", "永恒之井25人团队副本", "冰冠堡垒25人团队副本"};
    private int[] imgIds = new int[]{R.mipmap.list_item_img1, R.mipmap.list_item_img1, R.mipmap.list_item_img1, R.mipmap.list_item_img1};

//    private ListView list_book;
    private ListView list_app;

    private MyAdapter<App> myAdapter1 = null;
//    private MyAdapter<Book> myAdapter2 = null;

    private List<App> mData1 = null;
//    private List<Book> mData2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test5);
        init();
    }

    private void init() {
        dialog = new Dialog();

        list_app = (ListView)findViewById(R.id.list_item);
        mData1 = new ArrayList<App>();

        for(int i = 0; i < names.length; i++) {
            App app = new App(imgIds[i], names[i], says[i]);
            mData1.add(app);
        }

        myAdapter1 = new MyAdapter<App>((ArrayList)mData1, R.layout.list_item_icon) {
            @Override
            public void bindView(ViewHolder holder, App obj) {
                holder.setImageResource(R.id.imgIcon, obj.getaIcon());
                holder.setText(R.id.name, obj.getaName());
                holder.setText(R.id.says, obj.getSubName());
            }
        };
        list_app.setOnItemClickListener(this);
        list_app.setAdapter(myAdapter1);
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
