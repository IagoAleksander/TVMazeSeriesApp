package com.iaz.tvmazeseriesapp.repository.model.mapper

import com.iaz.tvmazeseriesapp.repository.model.Image
import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.repository.model.response.ImageDto
import com.iaz.tvmazeseriesapp.repository.model.response.ShowDto
import com.iaz.tvmazeseriesapp.repository.model.response.ShowSearchDto

fun ShowDto.toModel(): Show {
    return Show(
        this.id,
        this.name,
        this.image.toModel(),
        this.premiered,
        this.genres,
        this.summary
    )
}

fun ImageDto?.toModel(): Image {
    return Image(
        this?.medium ?: "",
        this?.original ?: ""
    )
}

fun ShowSearchDto.toModel(): Show {
    return this.show.toModel()
}