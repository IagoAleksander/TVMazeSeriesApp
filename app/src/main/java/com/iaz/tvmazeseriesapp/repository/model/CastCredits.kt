package com.iaz.tvmazeseriesapp.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastCredits(
    val _embedded: Embedded
) : Parcelable

@Parcelize
data class Embedded(
    val show: ShowBasic
) : Parcelable

@Parcelize
data class ShowBasic(
    val id: Int,
    val name: String
) : Parcelable