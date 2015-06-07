package com.excavator.advancedcontroltest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String, Object>> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mListView = (ListView) findViewById(R.id.listView);
        /**
         * 1.新建适配器
         * ArrayAdapter(上下文,当前ListView加载的每一个列表项所对应的布局文件,数据源)
         * 2.适配器加载数据源
         */
        String[] arrayData={"课程1","课程2","课程3","课程4","课程5"};
        mArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayData);
        /**
         * SimpleAdapter()
         * context:上下文
         * data:数据源 List<? extends Map<String ?>> data 一个Map所组成的List集合
         *      每一个Map对应ListView列表中的一行
         *      每一个Map(键-值对)中的键必须包含所有在from中所指定的键
         * resource:列表项的布局文件ID
         * from:Map中的键名
         * to:绑定数据视图中的ID，与from名称对应关系
         */
        mDataList = new ArrayList<Map<String, Object>>();
        mSimpleAdapter = new SimpleAdapter(this,getData(),R.layout.item,new String[]{"pic","text"},new int[]{R.id.pic,R.id.text});
        //3.视图(listView)加载适配器
//        mListView.setAdapter(mArrayAdapter);
        mListView.setAdapter(mSimpleAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);
    }

    private List<Map<String, Object>>  getData() {
        for (int i=0;i<20;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("pic",R.mipmap.ic_launcher);
            map.put("text","课程"+i);
            mDataList.add(map);
        }
        return mDataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text=mListView.getItemAtPosition(position)+"";
        Toast.makeText(this,"position="+position+"text="+text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("Main","用户在手指离开屏幕之前，由于用力滑了一下，视图仍依靠惯性继续滑动");
                Map<String, Object> map = new HashMap<>();
                map.put("pic", R.mipmap.ic_launcher);
                map.put("text","增加项");
                mDataList.add(map);
                //数据适配器下拉刷新
                mSimpleAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main","视图已经停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main","手指没有离开屏幕，视图正在滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
