package com.iaz.tvmazeseriesapp.repository.model.mapper

import com.iaz.tvmazeseriesapp.repository.model.Episode
import com.iaz.tvmazeseriesapp.repository.model.Image
import com.iaz.tvmazeseriesapp.repository.model.Schedule
import com.iaz.tvmazeseriesapp.repository.model.Season
import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.repository.model.response.EpisodeDto
import com.iaz.tvmazeseriesapp.repository.model.response.ImageDto
import com.iaz.tvmazeseriesapp.repository.model.response.ScheduleDto
import com.iaz.tvmazeseriesapp.repository.model.response.SeasonDto
import com.iaz.tvmazeseriesapp.repository.model.response.ShowDto
import com.iaz.tvmazeseriesapp.repository.model.response.ShowSearchDto

fun ShowDto.toModel(): Show {
    return Show(
        this.id,
        this.name,
        this.image.toModel(),
        this.schedule.toModel(),
        this.genres,
        this.summary
    )
}

fun ImageDto?.toModel(): Image {
    return Image(
        this?.medium,
        this?.original
    )
}

fun ScheduleDto?.toModel(): Schedule {
    return Schedule(
        this?.time,
        this?.days
    )
}

fun ShowSearchDto.toModel(): Show {
    return this.show.toModel()
}

fun SeasonDto.toModel(): Season {
    return Season(
        this.id,
        this.number,
        this.name,
    )
}

fun EpisodeDto.toModel(): Episode {
    return Episode(
        this.id,
        this.name,
        this.number,
        this.season,
        this.image.toModel(),
        this.summary
    )
}