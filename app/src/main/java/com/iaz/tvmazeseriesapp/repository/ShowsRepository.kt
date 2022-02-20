package com.iaz.tvmazeseriesapp.repository

import com.iaz.tvmazeseriesapp.repository.model.Show

interface ShowsRepository {
    suspend fun fetchShows(page: Int): List<Show>
    suspend fun fetchShowsByName(name: String): List<Show>
    suspend fun fetchShowDetailsById(id: Int): Show
}