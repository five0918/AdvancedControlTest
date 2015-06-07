package com.excavator.fragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends Activity {

    private Button mButton;
    private Fragment mFragment;
    private boolean mFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                if (mFlag) {
                    MyFragment4 fragment4 = new MyFragment4();
                    beginTransaction.replace(R.id.layout, fragment4);
                    mFlag = false;
                }else {
                    MyFragment3 fragment3 = new MyFragment3();
                    beginTransaction.replace(R.id.layout, fragment3);
                    mFlag = true;
                }
                beginTransaction.commit();
            }
        });
    }

    private void init() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        MyFragment3 fragment3 = new MyFragment3();
        beginTransaction.add(R.id.layout, fragment3);
        beginTransaction.commit();
    }

}
