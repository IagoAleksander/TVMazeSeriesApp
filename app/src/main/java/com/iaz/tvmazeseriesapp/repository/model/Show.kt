package com.iaz.tvmazeseriesapp.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    override val id: Int,
    override val name: String,
    override val image: Image,
    val premiered: String?,
    val genres: List<String>?,
    val summary: String?,
) : GridItem(id, name, image), Parcelable