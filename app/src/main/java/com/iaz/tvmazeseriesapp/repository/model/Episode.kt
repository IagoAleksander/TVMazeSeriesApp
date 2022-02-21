package com.iaz.tvmazeseriesapp.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Episode(
    val id: Int,
    val name: String,
    val number: Int,
    val season: Int,
    val image: Image,
    val summary: String?,
) : Parcelable