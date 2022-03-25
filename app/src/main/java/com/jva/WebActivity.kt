package com.jva

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jva.databinding.FragmentNewsBinding
import com.jva.databinding.WebActivityBinding


class WebActivity : AppCompatActivity() {
      private lateinit var _binding : WebActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.web_activity)

       val i = intent
        val url: String? = i.getSerializableExtra("news") as String?
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        _binding. webView.webViewClient = WebViewClient()

        // this will load_binding the url of the website
        url?.let {
            showProgress(true)
            _binding.webView.loadUrl(url)
        }


        // this will enable the javascript settings
        _binding.webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        _binding.webView.settings.setSupportZoom(true)

        _binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, weburl: String) {
                showProgress(false)
            }
        }
        _binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {

                if (newProgress == 100) {

                }
            }
        }
    }

    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (  _binding.webView.canGoBack())
            _binding.webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }
    private fun showProgress(show: Boolean) {
        _binding?.progressBar?.visibility = if (show) View.VISIBLE else View.GONE
    }
}