package com.excavator.webviewdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class WebViewActivity extends Activity {

    private static final String url = "http://www.imooc.com/course/list";
    private WebView mWebView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        /*Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/
        init();
    }

    private void init() {
        mWebView = (WebView) findViewById(R.id.webView);
        //加载本地资源 在java和res平行目录下新建assets文件夹(即在src/main目录下新建assets)
//        mWebView.loadUrl("file:///android_asset/example.html");
        //加载web资源
        mWebView.loadUrl(url);
        //覆盖WebView默认通过第三方或者系统浏览器打开网页，使得网页可以在WebView中打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true控制网页在WebView中打开，如果为false调用系统或第三方浏览器打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助页面处理一些页面控制和请求通知
        });

        //启用支持JavaScript
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //优先使用缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //判断页面加载进度
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //newProgress 1-100之间的整数
                if (newProgress==100) {
                    //网页加载完毕，关闭ProgressDialog
                    closeDialog();
                }else {
                    //网页正在加载,打开ProgressDialog
                    openDialog(newProgress);
                }
            }

            private void openDialog(int newProgress) {
                if (mProgressDialog==null) {
                    mProgressDialog = new ProgressDialog(WebViewActivity.this);
                    mProgressDialog.setTitle("正在加载");
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    mProgressDialog.setProgress(newProgress);
                    mProgressDialog.show();
                }else {
                    mProgressDialog.setProgress(newProgress);
                }
            }

            private void closeDialog() {
                if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        });
    }

    //改写物理按键返回逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,mWebView.getUrl(),Toast.LENGTH_SHORT).show();
            if (mWebView.canGoBack()){
                //返回上一界面
                mWebView.goBack();
                return true;
            }else {
                //退出程序
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
