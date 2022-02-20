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
            "No information available"
        } else {
            var listString = ""
            for (genre in list.subList(0, list.size - 1)) {
                listString += "$genre, "
            }
            listString += list[list.size - 1]
            listString
        }
    } ?: "No information available"
}

@BindingAdapter("handleHtmlFormattedString")
fun TextView.handleHtmlFormattedString(htmlString: String?) {
    text = htmlString?.let {
        HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString().trim()
    } ?: "No information available"
}

@BindingAdapter("handleLoading")
fun TextView.handleLoading(info: String?) {
    text = info ?: "No information available"
}