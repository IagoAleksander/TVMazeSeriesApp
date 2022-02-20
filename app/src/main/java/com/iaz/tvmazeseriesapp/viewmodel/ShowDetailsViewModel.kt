package com.iaz.tvmazeseriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iaz.tvmazeseriesapp.repository.ShowsRepository
import com.iaz.tvmazeseriesapp.repository.model.Show
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowDetailsViewModel(
    id: Int,
    private val repository: ShowsRepository
) : ViewModel() {

    private var mutableShow = MutableLiveData<Show>()

    val show: LiveData<Show>
        get() = mutableShow

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.fetchShowDetailsById(id).let { result ->
                    mutableShow.postValue(result)
                }
            }
        }
    }
}