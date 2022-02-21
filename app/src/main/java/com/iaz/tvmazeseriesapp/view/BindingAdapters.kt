package com.iaz.tvmazeseriesapp.view

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.iaz.tvmazeseriesapp.R

@BindingAdapter("loadImage")
fun bindLoadImage(view: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(url).placeholder(R.drawable.poster_placeholder).override(SIZE_ORIGINAL).into(view)
    }
}

@BindingAdapter("loadHorizontalImage")
fun bindLoadHorizontalImage(view: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(url).placeholder(R.drawable.poster_placeholder_horizontal).override(SIZE_ORIGINAL)
            .into(view)
    }
}

@BindingAdapter("handleStringList")
fun TextView.handleStringList(list: List<String>?) {
    text = list?.let {
        if (it.isEmpty()) {
            context.getString(R.string.no_information_available)
        } else {
            list.joinToString(", ")
        }
    } ?: context.getString(R.string.no_information_available)
}

@BindingAdapter("handleHtmlFormattedString")
fun TextView.handleHtmlFormattedString(htmlString: String?) {
    text = htmlString?.let {
        if (htmlString.isEmpty()) {
            context.getString(R.string.no_information_available)
        } else {
            HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString().trim()
        }
    } ?: context.getString(R.string.no_information_available)
}

@BindingAdapter("handleLoading")
fun TextView.handleLoading(info: String?) {
    if (info.isNullOrBlank()) {
        text = context.getString(R.string.no_information_available)
    } else {
        text = info
    }
}