package com.iaz.tvmazeseriesapp.repository.service

import com.iaz.tvmazeseriesapp.repository.model.CastCredits
import com.iaz.tvmazeseriesapp.repository.model.response.PeopleSearchDto
import com.iaz.tvmazeseriesapp.util.URLs.Companion.GET_CAST_CREDITS
import com.iaz.tvmazeseriesapp.util.URLs.Companion.GET_PEOPLE
import com.iaz.tvmazeseriesapp.util.URLs.Companion.SEARCH_PEOPLE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FetchPeopleService {
    @GET(SEARCH_PEOPLE)
    suspend fun fetchPeopleByName(@Query("q") name: String): List<PeopleSearchDto>

    @GET("$GET_PEOPLE/{id}/$GET_CAST_CREDITS?embed=show")
    suspend fun fetchCastCreditsByPersonId(@Path("id") id: Int): List<CastCredits>
}