package com.iaz.tvmazeseriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iaz.tvmazeseriesapp.repository.ShowsRepository
import com.iaz.tvmazeseriesapp.repository.model.Show
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    ids: List<String>,
    private val showsRepository: ShowsRepository
) : ViewModel() {

    private var mutableShows = MutableLiveData<List<Show>>()

    val shows: LiveData<List<Show>>
        get() = mutableShows

    init {
        fetchFavorites(ids)
    }

    fun fetchFavorites(ids: List<String>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchShowsDetails(ids).let { result ->
                    mutableShows.postValue(result)
                }
            }
        }
    }

    private suspend fun fetchShowsDetails(ids: List<String>): List<Show> {

        val showDetails = mutableListOf<Deferred<Show>>()
        for (id in ids) {
            try {
                showDetails.add(CoroutineScope(Dispatchers.IO).async {
                    showsRepository.fetchShowDetailsById(id.toInt())
                })
            } catch (e: Exception) {
                //TODO implement error state
            }
        }
        return showDetails.awaitAll()
    }
}

