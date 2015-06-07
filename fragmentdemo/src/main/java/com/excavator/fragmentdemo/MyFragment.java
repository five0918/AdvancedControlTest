package com.excavator.fragmentdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragment extends Fragment {

    private String a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //layout布局文件转换成View对象
        /**
         * resource：Fragment需要加载的布局文件
         * root: 加载layout的父ViewGroup
         * attachToRoot: false，不返回父ViewGroup
         */
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        Button button = (Button) view.findViewById(R.id.button);
        textView.setText("静态加载Fragment");
        button.setText("获取内容");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = getA();
                Toast.makeText(getActivity(), "value=" + value, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
