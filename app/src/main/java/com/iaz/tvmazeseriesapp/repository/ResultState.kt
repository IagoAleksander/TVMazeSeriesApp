package com.iaz.tvmazeseriesapp.repository

sealed class ResultState<out T>(open val data: T? = null){
    object Loading: ResultState<Nothing>()
    object Empty: ResultState<Nothing>()
    data class Loaded<out T>(override val data: T) : ResultState<T>()
}