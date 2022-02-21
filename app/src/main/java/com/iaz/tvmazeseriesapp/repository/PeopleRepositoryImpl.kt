package com.iaz.tvmazeseriesapp.repository

import com.iaz.tvmazeseriesapp.repository.model.mapper.toModel
import com.iaz.tvmazeseriesapp.repository.service.FetchPeopleService

class PeopleRepositoryImpl(
    private val service: FetchPeopleService
) : PeopleRepository {
    override suspend fun fetchPeopleByName(name: String) =
        service.fetchPeopleByName(name).map { it.toModel() }

    override suspend fun fetchCastCreditsByPersonId(id: Int) =
        service.fetchCastCreditsByPersonId(id)
}