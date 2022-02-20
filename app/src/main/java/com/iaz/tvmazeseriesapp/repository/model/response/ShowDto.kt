package com.iaz.tvmazeseriesapp.repository.model.response

data class ShowDto(
    val id: Int,
    val image: ImageDto?,
    val name: String,
    val premiered: String?,
    val genres: List<String>?,
    val summary: String?,
)

data class ImageDto(
    val medium: String?,
    val original: String?
)