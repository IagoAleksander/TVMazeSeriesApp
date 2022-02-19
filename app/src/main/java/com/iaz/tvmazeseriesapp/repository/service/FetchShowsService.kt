package com.iaz.tvmazeseriesapp.repository.service

import com.iaz.tvmazeseriesapp.repository.model.response.ShowDto
import com.iaz.tvmazeseriesapp.util.URLs.Companion.GET_SHOWS
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchShowsService {
    @GET(GET_SHOWS)
    suspend fun fetchShows(@Query("page") page: Int): List<ShowDto>
}