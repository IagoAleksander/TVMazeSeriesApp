package com.iaz.tvmazeseriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iaz.tvmazeseriesapp.repository.PeopleRepository
import com.iaz.tvmazeseriesapp.repository.ShowsRepository
import com.iaz.tvmazeseriesapp.repository.model.Person
import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.view.home.ShowPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val showsRepository: ShowsRepository,
    private val peopleRepository: PeopleRepository,
    private val dispatcherRequest: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private var mutableShows = MutableLiveData<List<Show>>()
    private var mutablePeople = MutableLiveData<List<Person>>()

    val shows: LiveData<List<Show>>
        get() = mutableShows

    val people: LiveData<List<Person>>
        get() = mutablePeople

    val flowShows = Pager(
        PagingConfig(PAGE_SIZE_DEFAULT)
    ) {
        ShowPagingSource(showsRepository)
    }.flow.cachedIn(viewModelScope)

    fun fetchShows(name: String) {
        viewModelScope.launch {
            withContext(dispatcherRequest) {
                showsRepository.fetchShowsByName(name)
                    .let { result ->
                        mutableShows.postValue(result)
                    }
            }
        }
    }

    fun fetchPeople(name: String) {
        viewModelScope.launch {
            withContext(dispatcherRequest) {
                peopleRepository.fetchPeopleByName(name)
                    .let { result ->
                        mutablePeople.postValue(result)
                    }
            }
        }
    }

    companion object {
        private const val PAGE_SIZE_DEFAULT = 250
    }
}

