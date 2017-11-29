package com.pen.corrupt;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.pen.wind.dialog.EnterDialog;
import com.pen.wind.dialog.EnterTwoDialog;
import com.pen.wind.dialog.PromptBoxDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_PromptDialog, R.id.btn_single_edit_dialog, R.id.btn_two_edit_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_PromptDialog:
                new PromptBoxDialog(this, R.style.PromptBoxDialog, "你确定删除这条信息吗？", new PromptBoxDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Toast.makeText(MainActivity.this, "确定删除", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "取消删除", Toast.LENGTH_SHORT).show();
                        }
                    }

                }).setTitle("温馨提醒").setNegativeButton("取消").setPositiveButton("确定").show();
                break;
            case R.id.btn_single_edit_dialog:

                new EnterDialog(this, R.style.PromptBoxDialog, "请输入光照警戒值", new EnterDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String editText) {
                        if (confirm) {
                            Toast.makeText(MainActivity.this, "***** " + editText, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "#####" + editText, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setTitle("温馨提醒").show();

                break;
            case R.id.btn_two_edit_dialog:
                new EnterTwoDialog(this, R.style.PromptBoxDialog, "请输入最小值", "请输入最大值", new EnterTwoDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, String editTextOne, String editTextTwo) {
                        if (confirm) {
                            Toast.makeText(MainActivity.this, "" + editTextOne + " ~ " + editTextTwo, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "" + editTextOne + " ~ " + editTextTwo, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setTitle("温馨提示").show();
                break;
        }
    }
}
