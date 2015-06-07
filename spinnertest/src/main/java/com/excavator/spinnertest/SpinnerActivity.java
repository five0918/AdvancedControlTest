package com.excavator.spinnertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private TextView mTextView;
    private Spinner mSpinner;
    private SimpleAdapter mSimpleAdatper;
    private List<Map<String, Object>> mDataList;
    private String[] arrayData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        mTextView = (TextView) findViewById(R.id.textView);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mTextView.setText("您选择的城市是：济南");
        arrayData = new String[]{"济南","烟台","青岛","威海","菏泽"};
        //设置数据源
        mDataList = new ArrayList<>();
        //新建数组适配器
        mSimpleAdatper = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"image", "text"}, new int[]{R.id.image, R.id.tv});
        //mAdapter设置下拉列表的样式
        mSimpleAdatper.setDropDownViewResource(R.layout.item);
        //加载适配器
        mSpinner.setAdapter(mSimpleAdatper);
        //Spinner设置监听器
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mTextView.setText("您选择的是：" + arrayData[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mTextView.setText("None");
    }
    public List<Map<String, Object>> getData(){
        for (int i=0;i<5;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", R.mipmap.ic_launcher);
            map.put("text",arrayData[i]);
            mDataList.add(map);
        }
        return mDataList;
    }
}
