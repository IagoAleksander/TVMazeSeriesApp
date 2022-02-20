package com.iaz.tvmazeseriesapp.repository

import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.repository.model.mapper.toModel
import com.iaz.tvmazeseriesapp.repository.service.FetchShowsService

class ShowsRepositoryImpl(
    private val service: FetchShowsService
) : ShowsRepository {
    override suspend fun fetchShows(page: Int): List<Show> {
        return service.fetchShows(page).map { it.toModel() }
    }

    override suspend fun fetchShowsByName(name: String): List<Show> {
        return service.fetchShowsByName(name).map { it.toModel() }
    }

    override suspend fun fetchShowDetailsById(id: Int): Show {
        return service.fetchShowDetailsById(id).toModel()
    }
}