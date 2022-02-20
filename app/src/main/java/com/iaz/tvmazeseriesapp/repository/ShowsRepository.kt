package com.iaz.tvmazeseriesapp.repository

import com.iaz.tvmazeseriesapp.repository.model.Episode
import com.iaz.tvmazeseriesapp.repository.model.Season
import com.iaz.tvmazeseriesapp.repository.model.Show

interface ShowsRepository {
    suspend fun fetchShows(page: Int): List<Show>
    suspend fun fetchShowsByName(name: String): List<Show>
    suspend fun fetchShowDetailsById(id: Int): Show
    suspend fun fetchSeasonsByShowId(id: Int): List<Season>
    suspend fun fetchEpisodesBySeasonId(id: Int): List<Episode>
}