package com.excavator.fragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends Activity implements MyFragment5.MyListener {

    private EditText mEditText;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mEditText = (EditText) findViewById(R.id.editText);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
                MyFragment5 fragment5 = new MyFragment5();
                Bundle bundle = new Bundle();
                bundle.putString("name",text);
                fragment5.setArguments(bundle);
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(R.id.layout,fragment5,"fragment5");
                beginTransaction.commit();
                Toast.makeText(MainActivity4.this,"向Fragment发送数据"+text,Toast.LENGTH_SHORT).show();
            }
        });
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.frag);
        MyFragment frag = (MyFragment) fragment;
        frag.setA("fragment静态传值");
    }

    @Override
    public void thank(String code) {
        Toast.makeText(MainActivity4.this,"成功接收到"+code+",客气了！",Toast.LENGTH_SHORT).show();
    }
}
