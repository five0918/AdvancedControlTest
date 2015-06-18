package com.excavator.fragmentdemo;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment5 extends Fragment {

    public MyListener listener;

    public interface MyListener{
        void thank(String code);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_fragment5, container, false);
        TextView tv = (TextView) view.findViewById(R.id.text);
        String text = getArguments().get("name")+"";
        tv.setText(text);
        Toast.makeText(getActivity(),"成功接收到"+text,Toast.LENGTH_SHORT).show();
        String code = "Thank you,Activity!";
        Toast.makeText(getActivity(),"向Activity发送"+ code,Toast.LENGTH_SHORT).show();
        listener.thank(code);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        listener = (MyListener) activity;
        super.onAttach(activity);
    }
}
