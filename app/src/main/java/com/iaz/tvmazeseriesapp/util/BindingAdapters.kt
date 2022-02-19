package com.iaz.tvmazeseriesapp.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.iaz.tvmazeseriesapp.R

@BindingAdapter("loadImage")
fun bindLoadImage(view: AppCompatImageView, url: String) {
    Glide.with(view.context).load(url).placeholder(R.drawable.poster_placeholder).override(SIZE_ORIGINAL).into(view)
}