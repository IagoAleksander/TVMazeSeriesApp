package com.iaz.tvmazeseriesapp.repository.model.response

data class PersonDto(
    val id: Int,
    val name: String,
    val image: ImageDto?,
    val gender: String?,
    val country: CountryDto?
)

data class CountryDto(
    val name: String
)


