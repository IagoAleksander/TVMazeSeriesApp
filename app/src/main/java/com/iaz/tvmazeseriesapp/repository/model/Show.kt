package com.iaz.tvmazeseriesapp.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    override val id: Int,
    override val name: String,
    override val image: Image,
    val schedule: Schedule?,
    val genres: List<String>?,
    val summary: String?,
) : GridItem(id, name, image), Parcelable

@Parcelize
data class Schedule(
    val time: String?,
    val days: List<String>?,
) : Parcelable