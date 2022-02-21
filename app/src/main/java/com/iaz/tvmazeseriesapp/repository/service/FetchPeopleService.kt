package com.iaz.tvmazeseriesapp.repository.service

import com.iaz.tvmazeseriesapp.repository.model.response.PeopleSearchDto
import com.iaz.tvmazeseriesapp.util.URLs.Companion.SEARCH_PEOPLE
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchPeopleService {
    @GET(SEARCH_PEOPLE)
    suspend fun fetchPeopleByName(@Query("q") name: String): List<PeopleSearchDto>
}