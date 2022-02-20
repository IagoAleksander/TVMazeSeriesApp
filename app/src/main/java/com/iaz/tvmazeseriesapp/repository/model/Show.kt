package com.iaz.tvmazeseriesapp.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    val id: Int,
    val name: String,
    val image: Image,
    val premiered: String?,
    val genres: List<String>?,
    val summary: String?,
) : Parcelable

@Parcelize
data class Image(
    val medium: String,
    val original: String
) : Parcelable