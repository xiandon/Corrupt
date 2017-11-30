package com.pen.corrupt;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by pen on 2017/11/29.
 */

public class ActivitySpinner extends AppCompatActivity {


    Spinner spSpinner;
    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<String>();
    private String TAG = "ActivitySpinner";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_spinner);
        spSpinner = findViewById(R.id.sp_spinner);

        list.add("AA");
        list.add("BB");
        list.add("CC");
        list.add("DD");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(adapter);

        spSpinner.setDropDownHorizontalOffset(300);
        spSpinner.setDropDownWidth(300);
        spSpinner.setDropDownVerticalOffset(300);
        spSpinner.setEnabled(false);

        spSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
