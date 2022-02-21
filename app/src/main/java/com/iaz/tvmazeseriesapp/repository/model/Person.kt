package com.iaz.tvmazeseriesapp.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    override val id: Int,
    override val name: String,
    override val image: Image,
    val gender: String?,
    val country: Country?
) : GridItem(id, name, image), Parcelable

@Parcelize
data class Country(
    val name: String?
) : Parcelable