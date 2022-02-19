package com.iaz.tvmazeseriesapp.repository.model.mapper

import com.iaz.tvmazeseriesapp.repository.model.Image
import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.repository.model.response.ImageDto
import com.iaz.tvmazeseriesapp.repository.model.response.ShowDto

fun ShowDto.toModel(): Show {
    return Show(
        this.id,
        this.name,
        this.image.toModel()
    )
}

fun ImageDto?.toModel(): Image {
    return Image(
        this?.medium ?: "",
        this?.original ?: ""
    )
}