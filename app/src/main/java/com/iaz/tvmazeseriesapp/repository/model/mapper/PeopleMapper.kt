package com.iaz.tvmazeseriesapp.repository.model.mapper

import com.iaz.tvmazeseriesapp.repository.model.Person
import com.iaz.tvmazeseriesapp.repository.model.response.PeopleSearchDto
import com.iaz.tvmazeseriesapp.repository.model.response.PersonDto

fun PersonDto.toModel(): Person {
    return Person(
        this.id,
        this.name,
        this.image.toModel(),
    )
}

fun PeopleSearchDto.toModel(): Person {
    return this.person.toModel()
}