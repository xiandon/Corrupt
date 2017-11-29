package com.pen.wind.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pen.wind.R;

/**
 * Created by pen on 2017/11/29.
 * 输入dialog
 */

public class EnterDialog extends Dialog implements View.OnClickListener {
    private EditText contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public EnterDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public EnterDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public EnterDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    public EnterDialog(Context context, int themeResId, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
    }


    protected EnterDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public EnterDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 正面按钮
     *
     * @param name
     * @return
     */
    public EnterDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    /**
     * 负面按钮
     *
     * @param name
     * @return
     */
    public EnterDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_enter);
        setCanceledOnTouchOutside(false);
        getWindow().getAttributes().gravity = Gravity.CENTER_VERTICAL; // 竖直居中
        initView();
    }

    private void initView() {
        titleTxt = findViewById(R.id.tv_enter_title);
        contentTxt = findViewById(R.id.et_enter_content);
        submitTxt = findViewById(R.id.tv_enter_submit);
        cancelTxt = findViewById(R.id.tv_enter_cancel);

        submitTxt.setOnClickListener(this);
        cancelTxt.setOnClickListener(this);

        contentTxt.setHint(content);

        if (!TextUtils.isEmpty(positiveName)) {
            submitTxt.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            cancelTxt.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_enter_cancel) {
            if (listener != null) {
                listener.onClick(this, false, contentTxt.getText().toString());
            }
            this.dismiss();

        } else if (i == R.id.tv_enter_submit) {
            if (listener != null) {
                listener.onClick(this, true, contentTxt.getText().toString());
            }

            this.dismiss();

        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm, String editText);
    }
}
