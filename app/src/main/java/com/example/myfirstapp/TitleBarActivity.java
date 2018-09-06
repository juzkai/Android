package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleBarActivity extends LinearLayout {
    private final String TAG = "-------：";

    private onLeftBtnClick leftListener;
    private onRightBtnClick rightListener;

    private TextView mTitle; //标题
    private String mTitleText;
    private int mTitleTextColor;

    private Button mLeftBtn; //左边按钮
    private boolean isLeftBtnVisible;
    private String mLeftBtnText;

    private Button mRightBtn; //右边按钮
    private boolean isRightBtnVisible;
    private String mRightBtnText;


    public TitleBarActivity(Context context) {
        this(context, null);
    }

    public TitleBarActivity(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public void initView(AttributeSet attrs){

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBarActivity);
        /** 标题栏文字、颜色 **/
        mTitleText = typedArray.getString(R.styleable.TitleBarActivity_title_text);
        mTitleTextColor = typedArray.getColor(R.styleable.TitleBarActivity_title_text_color, getResources().getColor(R.color.colorWhite));
        /** 左边按钮是否可见 **/
        isLeftBtnVisible = typedArray.getBoolean(R.styleable.TitleBarActivity_left_btn_visible, false);
        /** 右边按钮是否可见 **/
        isRightBtnVisible = typedArray.getBoolean(R.styleable.TitleBarActivity_right_btn_visible, false);
        /** 左边按钮文字 **/
        mLeftBtnText = typedArray.getString(R.styleable.TitleBarActivity_left_btn_text);
        /** 右边按钮文字 **/
        mRightBtnText = typedArray.getString(R.styleable.TitleBarActivity_right_btn_text);

        typedArray.recycle();

        /** 设置内容 **/
        View barLayoutView = View.inflate(getContext(), R.layout.activity_title_bar, null);

//        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        addView(barLayoutView, 0);
        mLeftBtn = (Button)barLayoutView.findViewById(R.id.mTitleLeft);
        mRightBtn = (Button)barLayoutView.findViewById(R.id.mTitleRight);
        /** 注意这里不能直接用findViewById,因为setContentView之后使用findViewById是在当前layout之下找，当前layout找不到，于是就null了 **/
        mTitle = (TextView)barLayoutView.findViewById(R.id.mTitleText);
        setTitleText(mTitleText);
        setTitleTextColor(mTitleTextColor);
        setLeftBtnVisible(isLeftBtnVisible);
        if(mLeftBtnText != null) {
            setLeftBtnText(mLeftBtnText);
        }

        setRightVisible(isRightBtnVisible);
        setRightBtnText(mRightBtnText);
        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(leftListener == null) {
                    ((Activity)getContext()).finish();
                } else {
                    leftListener.onLeftBtnClick();
                }
            }
        });

        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rightListener == null) {
                    return;
                } else {
                    rightListener.onRightBtnClick();
                }
            }
        });

    }

    /**
     * 设置标题文字
     * @param title
     */
    public void setTitleText(CharSequence title) {
        mTitle.setText(title);
    }

    /**
     * 设置标题栏文字颜色
     * @param color
     */
    public void setTitleTextColor(int color) {
        mTitle.setTextColor(color);
    }

    /**
     * 设置左边按钮是否可见
     * @param visible
     */
    public void setLeftBtnVisible(boolean visible) {
        if(visible) {
            mLeftBtn.setVisibility(View.VISIBLE);
        } else {
            mLeftBtn.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置左边按钮文字
     * @param btnText
     */
    public void setLeftBtnText(CharSequence btnText) {
        mLeftBtn.setText(btnText);
    }

    /**
     * 设置右边按钮是否可见
     * @param visible
     */
    public void setRightVisible(boolean visible) {
        if(visible) {
            mRightBtn.setVisibility(View.VISIBLE);
        } else {
            mRightBtn.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置右边按钮文字
     * @param btnText
     */
    public void setRightBtnText(CharSequence btnText) {
        mRightBtn.setText(btnText);
    }

    /**
     *  设置左边按钮监听器
     * @param leftListener
     */
    public void setOnLeftBtnClick(onLeftBtnClick leftListener) {
        this.leftListener = leftListener;
    }

    /**
     *设置右边按钮监听器
     * @param rightListener
     */
    public void setOnRightBtnClick(onRightBtnClick rightListener) {
        this.rightListener = rightListener;
    }

    /**
     *  左边按钮点击回调
     */
    public interface onLeftBtnClick{
        void onLeftBtnClick();
    }

    /**
     *  右边按钮点击回调
     */
    public interface onRightBtnClick{
        void onRightBtnClick();
    }
}
