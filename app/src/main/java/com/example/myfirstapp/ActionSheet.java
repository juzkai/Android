package com.example.myfirstapp;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.myfirstapp.base.MyAdapter;
import com.example.myfirstapp.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUZENGKAI on 2018/9/6.
 */

public class ActionSheet extends Dialog implements AdapterView.OnItemClickListener{
    private Button mCancel;
    private CancelClickListener cancelListener;
    private ItemClickListener itemClickListener;
    private ListView mMenuItems;
    private MyAdapter<Item> mAdapter;
    private List<Item> items;
    public ActionSheet(@NonNull Context context) {
        super(context, R.style.ActionSheetDialog);
        initView(context);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    public void initView (Context context) {
        View view = View.inflate(context, R.layout.activity_action_sheet, null);
        mCancel = (Button)view.findViewById(R.id.menu_cancel);
        mMenuItems = (ListView)view.findViewById(R.id.menu_items);
        items = new ArrayList<Item>();
        mAdapter = new MyAdapter<Item>((ArrayList<Item>) items, R.layout.menu_item) {
            @Override
            public void bindView(ViewHolder holder, Item obj) {
                holder.setText(R.id.item_name, obj.getItemName());
            }
        };
        mMenuItems.setOnItemClickListener(this);
        mMenuItems.setAdapter(mAdapter);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelListener == null) {
                    dismiss();
                } else {
                    cancelListener.onCancelClick();
                }
            }
        });
        this.setContentView(view);
    }

    /**
     * 展示
     */
    @Override
    public void show () {
        mAdapter.notifyDataSetChanged();
        super.show();
    }

    /**
     * 添加item
     * @param item
     */
    public void addItem (Item item) {
        items.add(item);
        mAdapter.notifyDataSetChanged();
    }
    public void destory () {
        dismiss();
    }
    /**
     * item点击事件
     * @param adapterView
     * @param view
     * @param index
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(index);
        }
    }

    /**
     * item点击事件监听器
     * @param itemClickListener
     */
    public void setItemClickListener (ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    /**
     * 取消按钮监听器
     * @param cancelListener
     */
    public void setCancelListener(CancelClickListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    /**
     *  取消按钮点击回调
     */
    public interface CancelClickListener {
        void onCancelClick();
    }
    public interface ItemClickListener {
        void onItemClick(int index);
    }
}
