package com.example.myfirstapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.myfirstapp.base.MyAdapter;
import com.example.myfirstapp.entity.Icon;
import com.example.myfirstapp.entity.Item;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private Context mContext;
    private GridView grid_photo;
    private BaseAdapter mAdapter;
    private ArrayList<Icon> mData = null;

    private Dialog dialog;

    private Intent intent = null;

    private TitleBarActivity mTitle;
    private String[] strings;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    private ArrayList<Integer> choice;

    private String singles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置Activity全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mTitle = (TitleBarActivity)findViewById(R.id.title_bar);
        mTitle.setOnRightBtnClick(new TitleBarActivity.onRightBtnClick() {
            @Override
            public void onRightBtnClick() {
                dialog.showToast(MainActivity.this, "您点击了提交按钮");
            }
        });
        init();
    }

    protected void init() {
        dialog = new Dialog();
        strings = getResources().getStringArray(R.array.heroData);
        singles = strings[0];
        mContext = MainActivity.this;
        grid_photo = (GridView)findViewById(R.id.grid_photo);
        mData = new ArrayList<Icon>();
        for (int i = 1; i < 13; i++) {
            Icon icon = new Icon(R.mipmap.list_item_img1, "item" + i);
            mData.add(icon);
        }

        mAdapter = new MyAdapter<Icon>(mData, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.text_icon, obj.getiName());
            }
        };

        grid_photo.setAdapter(mAdapter);

        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog.showToast(mContext, "第" + (i + 1) + "项");
                boolean flag = true;
                switch (i) {
                    case 0:
                        intent = new Intent(mContext, Test1Activity.class);
                        break;
                    case 1:
                        intent = new Intent(mContext, Test2Activity.class);
                        break;
                    case 2:
                        intent = new Intent(mContext, Test3Activity.class);
                        break;
                    case 3:
                        intent = new Intent(mContext, Test4Activity.class);
                        break;
                    case 4:
                        intent = new Intent(mContext, Test5Activity.class);
                        break;
                    case 5:
                        intent = new Intent(mContext, WebviewEntry.class);
                        break;
                    case 6:
                        intent = null;
                        flag = false;
                        builder = new AlertDialog.Builder(mContext);
                        builder.setCancelable(false);
                        builder.setItems(R.array.heroData, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.showToast(mContext, "选择了" + strings[i]);
                            }
                        });
                        alertDialog = builder.create();
                        alertDialog.show();
                        break;
                    case 7:
                        intent = null;
                        flag = false;
                        builder = new AlertDialog.Builder(mContext);
                        builder.setCancelable(false);
                        builder.setTitle("选择段位");
                        choice = new ArrayList();
                        builder.setMultiChoiceItems(R.array.heroData, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                if (b) {
                                    choice.add(i);
                                } else if(choice.contains(i)) {
                                    choice.remove(Integer.valueOf(i));
                                }
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String choices = "";
                                for (int k = 0; k < choice.size(); k++) {
                                    choices += strings[choice.get(k)] + ",";
                                }
                                Log.d("item8:", choices);
                                dialog.showToast(mContext, "选择了" + choices);
                            }
                        });
                        alertDialog = builder.create();
                        alertDialog.show();
                        break;
                    case 8:
                        intent = null;
                        flag = false;
                        builder = new AlertDialog.Builder(mContext);
                        builder.setCancelable(false);
                        builder.setTitle("选择段位");
                        // 注意这里的setSingleChoiceItems参数中checkedItem不能为null 必须有一个选中的值
                        builder.setSingleChoiceItems(R.array.heroData, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                singles = strings[i];
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.showToast(mContext, "选中了" + singles);
                            }
                        });
                        alertDialog = builder.create();
                        alertDialog.show();
                        break;
                    case 9:
                        flag = false;
                        ActionSheet actionSheet = new ActionSheet(mContext);
                        actionSheet.addItem(new Item(1, "item3"));
                        actionSheet.setItemClickListener(new ActionSheet.ItemClickListener() {
                            @Override
                            public void onItemClick(int index) {
                                Log.d("你选择了----", Integer.toString(index));
                            }
                        });
                        actionSheet.show();
                        break;
                    default:
                        flag = false;
                        break;
                }
                if(flag == true) {
                    startActivity(intent);
                }
            }
        });

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


    @Override
    public void onClick(View view) {
//        int id = view.getId();

//        switch (id) {
//            case R.id.textView1:
//                intent = new Intent(this, Test1Activity.class);
//                break;
//            case R.id.textView2:
//                intent = new Intent(this, Test2Activity.class);
//                break;
//            case R.id.textView3:
//                intent = new Intent(this, Test3Activity.class);
//                break;
//            case R.id.textView4:
//                intent = new Intent(this, Test4Activity.class);
//                break;
//            case R.id.textView5:
//                intent = new Intent(this, Test5Activity.class);
//            default: break;
//        }
//        startActivity(intent);
    }
}
