package com.iaz.tvmazeseriesapp.repository.service

import com.iaz.tvmazeseriesapp.repository.model.response.EpisodeDto
import com.iaz.tvmazeseriesapp.repository.model.response.SeasonDto
import com.iaz.tvmazeseriesapp.repository.model.response.ShowDto
import com.iaz.tvmazeseriesapp.repository.model.response.ShowSearchDto
import com.iaz.tvmazeseriesapp.util.URLs.Companion.GET_EPISODES
import com.iaz.tvmazeseriesapp.util.URLs.Companion.GET_SEASONS
import com.iaz.tvmazeseriesapp.util.URLs.Companion.GET_SHOWS
import com.iaz.tvmazeseriesapp.util.URLs.Companion.SEARCH_SHOWS
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FetchShowsService {
    @GET(GET_SHOWS)
    suspend fun fetchShows(@Query("page") page: Int): List<ShowDto>

    @GET(SEARCH_SHOWS)
    suspend fun fetchShowsByName(@Query("q") name: String): List<ShowSearchDto>

    @GET("$GET_SHOWS/{id}")
    suspend fun fetchShowDetailsById(@Path("id") id: Int): ShowDto

    @GET("$GET_SHOWS/{id}/$GET_SEASONS")
    suspend fun fetchSeasonsByShowId(@Path("id") id: Int): List<SeasonDto>

    @GET("$GET_SEASONS/{id}/$GET_EPISODES")
    suspend fun fetchEpisodesBySeasonId(@Path("id") id: Int): List<EpisodeDto>
}