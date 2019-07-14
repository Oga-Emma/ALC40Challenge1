package com.a7apps.alc40challenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.graphics.Bitmap
import android.view.View
import android.widget.ProgressBar


class AboutALCActivity : AppCompatActivity() {
    lateinit var mLoadingProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_alc)

        title = getString(R.string.about_alc_label)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mLoadingProgress = findViewById(R.id.loading_progress_bar)

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.loadWithOverviewMode = true
        myWebView.settings.useWideViewPort = true
        myWebView.settings.builtInZoomControls = true
        myWebView.webViewClient = ALCWebViewClient()
        myWebView.loadUrl("https://andela.com/alc/")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private inner class ALCWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return false
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, er: SslError) {
            handler.proceed()
            // Ignore SSL certificate errors
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            mLoadingProgress.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView, url: String) {
            mLoadingProgress.visibility = View.GONE
        }
    }
}
