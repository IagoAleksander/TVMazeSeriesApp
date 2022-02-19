package com.iaz.tvmazeseriesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iaz.tvmazeseriesapp.repository.service.FetchShowsService
import com.iaz.tvmazeseriesapp.view.ShowPagingAdapter

class HomeShowViewModel(
    private val fetchShowsService: FetchShowsService
) : ViewModel() {
    val flowShows = Pager(
        PagingConfig(PAGE_SIZE_DEFAULT)
    ) {
        ShowPagingAdapter(fetchShowsService)
    }.flow.cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE_DEFAULT = 250
    }
}

