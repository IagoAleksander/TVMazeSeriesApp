package com.iaz.tvmazeseriesapp.repository.model.response

data class ShowDto(
    val id: Int,
    val image: ImageDto?,
    val name: String,
    val schedule: ScheduleDto?,
    val genres: List<String>?,
    val summary: String?,
)

data class ScheduleDto(
    val time: String?,
    val days: List<String>?,
)