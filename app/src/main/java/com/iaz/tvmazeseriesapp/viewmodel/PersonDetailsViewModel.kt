package com.iaz.tvmazeseriesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iaz.tvmazeseriesapp.repository.PeopleRepository
import com.iaz.tvmazeseriesapp.repository.model.CastCredits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonDetailsViewModel(
    id: Int,
    private val repository: PeopleRepository
) : ViewModel() {

    private var mutableCastCredits = MutableLiveData<List<CastCredits>>()

    val castCredits: LiveData<List<CastCredits>>
        get() = mutableCastCredits

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.fetchCastCreditsByPersonId(id).let { result ->
                    mutableCastCredits.postValue(result)
                }
            }
        }
    }
}