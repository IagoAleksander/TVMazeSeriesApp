package com.iaz.tvmazeseriesapp.repository

import com.iaz.tvmazeseriesapp.repository.model.Person

interface PeopleRepository {
    suspend fun fetchPeopleByName(name: String): List<Person>
}