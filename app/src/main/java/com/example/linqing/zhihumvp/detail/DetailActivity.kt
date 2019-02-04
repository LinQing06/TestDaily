package com.example.linqing.zhihumvp.detail

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView

import com.example.linqing.zhihumvp.R
import com.example.linqing.zhihumvp.entitybean.ArticleDetailBean


class DetailActivity : AppCompatActivity(), DetailContract.DetailView {
    private val presenterInter = DetailPresenter(this)
    private var webView: WebView? = null
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)
        val intent = intent
        id = intent.getStringExtra("id")
        presenterInter.getIdP(id!!)

        init()

        presenterInter.getWebviewData()

    }

    private fun init() {
        webView = findViewById<View>(R.id.webview_wv) as WebView?

        val settings = webView!!.settings

        settings.defaultFontSize = 48
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

        //支持获取手势焦点
        webView!!.requestFocusFromTouch()
        //支持JS
        settings.javaScriptEnabled = true
        //支持插件
        //settings.pluginState = WebSettings.PluginState.ON
        //设置适应屏幕
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        //支持缩放
        settings.setSupportZoom(true)
        // 显示缩放按钮(wap网页不支持)
        settings.builtInZoomControls = true
        //隐藏原生缩放控件
        settings.displayZoomControls = false
        //支持内容重新布局
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.supportMultipleWindows()
        settings.setSupportMultipleWindows(true)
        //设置缓存模式
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCacheEnabled(true)
        settings.setAppCachePath(webView!!.context.cacheDir.absolutePath)

        //设置可访问文件
        settings.allowFileAccess = true
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true)
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            settings.loadsImagesAutomatically = true
        } else {
            settings.loadsImagesAutomatically = false
        }
        settings.setNeedInitialFocus(true)
        //设置编码格式
        settings.defaultTextEncodingName = "UTF-8"
    }


    override fun loadWebDetail(data: ArticleDetailBean) {


        webView!!.loadDataWithBaseURL(null, data.body, "text/html", "UTF-8", null)

    }

}
