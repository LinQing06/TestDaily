package com.example.linqing.zhihumvp.detail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.linqing.zhihumvp.R;
import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean;


public class DetailActivity extends AppCompatActivity implements DetailContract.DetailView {
    private DetailContract.DetailPresenterInter presenterInter = new DetailPresenter(this);
    private WebView webView;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        presenterInter.getIdP(id);

        init();

        presenterInter.getWebviewData();

    }

    private void init() {
        webView = (WebView) findViewById(R.id.webview_wv);

        WebSettings settings = webView.getSettings();

        settings.setDefaultFontSize(48);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //支持获取手势焦点
        webView.requestFocusFromTouch();
        //支持JS
        settings.setJavaScriptEnabled(true);
        //支持插件
        settings.setPluginState(WebSettings.PluginState.ON);
        //设置适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持缩放
        settings.setSupportZoom(true);
        // 显示缩放按钮(wap网页不支持)
        settings.setBuiltInZoomControls(true);
        //隐藏原生缩放控件
        settings.setDisplayZoomControls(false);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.supportMultipleWindows();
        settings.setSupportMultipleWindows(true);
        //设置缓存模式
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(webView.getContext().getCacheDir().getAbsolutePath());

        //设置可访问文件
        settings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setNeedInitialFocus(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
    }


    @Override
    public void loadWebDetail(ArticleDetailBean data) {


        webView.loadDataWithBaseURL(null, data.body, "text/html", "UTF-8", null);

    }

}
