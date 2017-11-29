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

public class EnterTwoDialog extends Dialog implements View.OnClickListener {
    private EditText contentTxtOne;
    private EditText contentTxtTwo;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private Context mContext;
    private String contentOne;
    private String contentTwo;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public EnterTwoDialog(Context context) {
        super(context);
        this.mContext = context;
    }


    public EnterTwoDialog(Context context, int themeResId, String contentOne, String contentTwo, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.contentOne = contentOne;
        this.contentTwo = contentTwo;
        this.listener = listener;
    }


    public EnterTwoDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 正面按钮
     *
     * @param name
     * @return
     */
    public EnterTwoDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    /**
     * 负面按钮
     *
     * @param name
     * @return
     */
    public EnterTwoDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_enter_two);
        setCanceledOnTouchOutside(false);
        getWindow().getAttributes().gravity = Gravity.CENTER_VERTICAL; // 竖直居中
        initView();
    }

    private void initView() {
        titleTxt = findViewById(R.id.tv_enter_two_title);
        contentTxtOne = findViewById(R.id.et_enter_one);
        contentTxtTwo = findViewById(R.id.et_enter_two);
        submitTxt = findViewById(R.id.tv_enter_two_submit);
        cancelTxt = findViewById(R.id.tv_enter_two_cancel);

        submitTxt.setOnClickListener(this);
        cancelTxt.setOnClickListener(this);

        contentTxtOne.setHint(contentOne);
        contentTxtTwo.setHint(contentTwo);

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
        if (i == R.id.tv_enter_two_cancel) {
            if (listener != null) {
                listener.onClick(this, false, contentTxtOne.getText().toString(), contentTxtTwo.getText().toString());
            }
            this.dismiss();

        } else if (i == R.id.tv_enter_two_submit) {
            if (listener != null) {
                listener.onClick(this, true, contentTxtOne.getText().toString(), contentTxtTwo.getText().toString());
            }

            this.dismiss();

        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm, String editTextOne, String editTextTwo);
    }
}
