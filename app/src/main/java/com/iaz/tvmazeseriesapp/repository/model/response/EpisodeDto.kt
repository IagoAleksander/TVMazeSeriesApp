package com.iaz.tvmazeseriesapp.repository.model.response

data class EpisodeDto(
    val id: Int,
    val name: String,
    val number: Int,
    val image: ImageDto?,
    val summary: String?,
)

