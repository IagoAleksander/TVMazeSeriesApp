package com.iaz.tvmazeseriesapp.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    override val id: Int,
    override val name: String,
    override val image: Image,
) : GridItem(id, name, image), Parcelable