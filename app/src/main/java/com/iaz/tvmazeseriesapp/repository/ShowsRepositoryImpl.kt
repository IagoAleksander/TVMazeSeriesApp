package com.iaz.tvmazeseriesapp.repository

import com.iaz.tvmazeseriesapp.repository.model.mapper.toModel
import com.iaz.tvmazeseriesapp.repository.service.FetchShowsService

class ShowsRepositoryImpl(
    private val service: FetchShowsService
) : ShowsRepository {
    override suspend fun fetchShows(page: Int) =
        service.fetchShows(page).map { it.toModel() }

    override suspend fun fetchShowsByName(name: String) =
        service.fetchShowsByName(name).map { it.toModel() }

    override suspend fun fetchShowDetailsById(id: Int) =
        service.fetchShowDetailsById(id).toModel()

    override suspend fun fetchSeasonsByShowId(id: Int) =
        service.fetchSeasonsByShowId(id).map { it.toModel() }

    override suspend fun fetchEpisodesBySeasonId(id: Int) =
        service.fetchEpisodesBySeasonId(id).map { it.toModel() }
}