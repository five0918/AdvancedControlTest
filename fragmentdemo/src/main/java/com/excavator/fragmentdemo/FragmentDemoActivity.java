package com.excavator.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

public class FragmentDemoActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.first: {
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            }
            case R.id.second: {
                MyFragment2 fragment2 = new MyFragment2();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(R.id.frame, fragment2);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            }
            case R.id.third: {
                Intent intent = new Intent(this, MainActivity3.class);
                startActivity(intent);
                break;
            }
            case R.id.fourth: {
                Intent intent = new Intent(this, MainActivity4.class);
                startActivity(intent);
                break;
            }

        }
    }
}
