package com.iaz.tvmazeseriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iaz.tvmazeseriesapp.repository.ShowsRepository
import com.iaz.tvmazeseriesapp.repository.model.Episode
import com.iaz.tvmazeseriesapp.repository.model.Season
import com.iaz.tvmazeseriesapp.repository.model.Show
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowDetailsViewModel(
    id: Int,
    private val repository: ShowsRepository
) : ViewModel() {

    private var mutableShow = MutableLiveData<Show>()
    private var mutableSeasons = MutableLiveData<List<Season>>()
    private var mutableEpisodes = MutableLiveData<List<Episode>>()

    val show: LiveData<Show>
        get() = mutableShow

    val seasons: LiveData<List<Season>>
        get() = mutableSeasons

    val episodes: LiveData<List<Episode>>
        get() = mutableEpisodes

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.fetchShowDetailsById(id).let { result ->
                    mutableShow.postValue(result)
                }
                repository.fetchSeasonsByShowId(id).let { result ->
                    mutableSeasons.postValue(result)
                }
            }
        }
    }

    fun fetchEpisodesBySeasonId(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.fetchEpisodesBySeasonId(id).let { result ->
                    mutableEpisodes.postValue(result)
                }
            }
        }
    }
}