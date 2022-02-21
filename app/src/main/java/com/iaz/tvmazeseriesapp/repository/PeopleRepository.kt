package com.iaz.tvmazeseriesapp.repository

import com.iaz.tvmazeseriesapp.repository.model.CastCredits
import com.iaz.tvmazeseriesapp.repository.model.Person

interface PeopleRepository {
    suspend fun fetchPeopleByName(name: String): List<Person>
    suspend fun fetchCastCreditsByPersonId(id: Int): List<CastCredits>
}