package com.pen.corrupt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pen.wind.custom.TempControlView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textTempControlView)
    TempControlView textTempControlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textTempControlView.setTemp(15,35,20.21);

    }
}
