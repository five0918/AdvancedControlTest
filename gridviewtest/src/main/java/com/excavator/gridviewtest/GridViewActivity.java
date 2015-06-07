package com.excavator.gridviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewActivity extends Activity implements AdapterView.OnItemClickListener{

    private GridView mGridView;
    private List<Map<String, Object>> mDataList;
    private int[] icon = {R.drawable.address_book, R.drawable.calendar, R.drawable.camera,
            R.drawable.clock, R.drawable.games_control, R.drawable.messenger, R.drawable.ringtone,
            R.drawable.settings, R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
            R.drawable.youtube};
    private String[] iconName={"通讯录","日历","照相机","时钟","游戏","短信","铃声","设置","语音",
            "天气","浏览器","视频"};
    private SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        mGridView = (GridView) findViewById(R.id.gridView);
        //1.准备数据源
        mDataList = new ArrayList<>();

        //2.新建适配器
        mSimpleAdapter = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"image","text"}, new int[]{R.id.image, R.id.textView1});
        //3.GridView加载适配器
        mGridView.setAdapter(mSimpleAdapter);
        //4.GridView配置监听器OnItemClickListener
        mGridView.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> getData() {
        //填充数据源
        for (int i=0;i<icon.length;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image",icon[i]);
            map.put("text", iconName[i]);
            mDataList.add(map);
        }
        return mDataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"点击了"+iconName[position],Toast.LENGTH_SHORT).show();
    }
}
