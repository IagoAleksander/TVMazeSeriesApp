package com.iaz.tvmazeseriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iaz.tvmazeseriesapp.repository.PeopleRepository
import com.iaz.tvmazeseriesapp.repository.ResultState
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

    private var mutableResultStateShows = MutableLiveData<ResultState<List<Show>>>()
    private var mutableResultStatePeople = MutableLiveData<ResultState<List<Person>>>()

    val resultStateShows: LiveData<ResultState<List<Show>>>
        get() = mutableResultStateShows

    val resultStatePeople: LiveData<ResultState<List<Person>>>
        get() = mutableResultStatePeople

    val flowShows = Pager(
        PagingConfig(PAGE_SIZE_DEFAULT)
    ) {
        ShowPagingSource(showsRepository)
    }.flow.cachedIn(viewModelScope)

    fun fetchShows(name: String) {
        mutableResultStateShows.postValue(ResultState.Loading)

        viewModelScope.launch {
            withContext(dispatcherRequest) {
                showsRepository.fetchShowsByName(name)
                    .let { result ->
                        if (result.isEmpty()) {
                            mutableResultStateShows.postValue(ResultState.Empty)
                        } else {
                            mutableResultStateShows.postValue(ResultState.Loaded(result))
                        }
                    }
            }
        }
    }

    fun fetchPeople(name: String) {
        mutableResultStateShows.postValue(ResultState.Loading)

        viewModelScope.launch {
            withContext(dispatcherRequest) {
                peopleRepository.fetchPeopleByName(name)
                    .let { result ->
                        if (result.isEmpty()) {
                            mutableResultStatePeople.postValue(ResultState.Empty)
                        } else {
                            mutableResultStatePeople.postValue(ResultState.Loaded(result))
                        }
                    }
            }
        }
    }

    companion object {
        private const val PAGE_SIZE_DEFAULT = 250
    }
}

