package com.excavator.progressbarapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressBarDemo extends Activity implements View.OnClickListener {

    private ProgressBar mProgressBar;
    private Button add;
    private Button reduce;
    private Button reset;
    private TextView mText;
    private ProgressDialog mProgressDialog;
    private Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_demo);
        init();
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    private void init() {
        mProgressBar = (ProgressBar) findViewById(R.id.horizon);
        add = (Button) findViewById(R.id.add);
        reduce = (Button) findViewById(R.id.reduce);
        reset = (Button) findViewById(R.id.reset);
        mText = (TextView) findViewById(R.id.textView);
        show = (Button) findViewById(R.id.show);
        //获取第一进度条和第二进度条的进度
        int first = mProgressBar.getProgress();
        int second = mProgressBar.getSecondaryProgress();
        //获取进度条的最大进度
        int max = mProgressBar.getMax();
        mText.setText("第一进度条百分比：" + (int) (first / (float) max * 100) + "% 第二进度条的百分比：" + (int) (second / (float) max * 100) + "%");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                //增加第一进度和第二进度10个刻度
                mProgressBar.incrementProgressBy(10);
                mProgressBar.incrementSecondaryProgressBy(10);
                break;
            case R.id.reduce:
                //减少第一进度和第二进度10个刻度
                mProgressBar.incrementProgressBy(-10);
                mProgressBar.incrementSecondaryProgressBy(-10);
                break;
            case R.id.reset:
                mProgressBar.setProgress(50);
                mProgressBar.setSecondaryProgress(80);
                break;
            case R.id.show:
                //新建ProgressDialog对象
                mProgressDialog=new ProgressDialog(ProgressBarDemo.this);
                //设置显示风格
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //设置标题
                mProgressDialog.setTitle("Android");
                //设置对话框文字信息
                mProgressDialog.setMessage("Hello Android!");
                //设置图标
                mProgressDialog.setIcon(R.mipmap.ic_launcher);
                /**
                 * 设定关于ProgressDialog的一些属性
                 */
                //设定最大进度
                mProgressDialog.setMax(100);
                //设定初始化已经增长到的进度
                mProgressDialog.incrementProgressBy(50);
                //进度条是明确显示进度的
                mProgressDialog.setIndeterminate(false);
                /**
                 * 设定一个确定按钮
                 */
                mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProgressBarDemo.this,"欢迎进入Android世界！",Toast.LENGTH_SHORT).show();
                    }
                });

                //是否可以通过返回按钮退出对话框
                mProgressDialog.setCancelable(true);
                //显示ProgressDialog
                mProgressDialog.show();
                break;
        }
        mText.setText("第一进度条百分比：" + (int) (mProgressBar.getProgress() / (float) mProgressBar.getMax() * 100) + "% 第二进度条的百分比：" + (int) (mProgressBar.getSecondaryProgress()/ (float) mProgressBar.getMax() * 100) + "%");
    }
}
