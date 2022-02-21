package com.iaz.tvmazeseriesapp.repository.model.mapper

import com.iaz.tvmazeseriesapp.repository.model.Country
import com.iaz.tvmazeseriesapp.repository.model.Person
import com.iaz.tvmazeseriesapp.repository.model.response.CountryDto
import com.iaz.tvmazeseriesapp.repository.model.response.PeopleSearchDto
import com.iaz.tvmazeseriesapp.repository.model.response.PersonDto

fun PersonDto.toModel(): Person {
    return Person(
        this.id,
        this.name,
        this.image.toModel(),
        this.gender,
        this.country.toModel()
    )
}

fun PeopleSearchDto.toModel(): Person {
    return this.person.toModel()
}

fun CountryDto?.toModel(): Country {
    return Country(
        this?.name
    )
}