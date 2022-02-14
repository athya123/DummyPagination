package com.tare.newsapp.utils

import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tare.newsapp.pojo.entities.Article

@BindingAdapter("setDate")
fun bindDate(textView: TextView, date: String?) {
    if (!date.isNullOrEmpty()) {
        textView.text = DateHelper.formatDate(date)
    }
}

@BindingAdapter("setImage")
fun bindImage(imageView: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}

@BindingAdapter("openNews")
fun bindNews(webView: WebView, item: Article?) {
    item?.let {
        webView.startAnimation(
            AnimationUtils.loadAnimation(
                webView.context,
                android.R.anim.slide_in_left
            )
        )
        webView.settings.javaScriptEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView.loadUrl(item.url)
        webView.visibility = View.VISIBLE
    }
}