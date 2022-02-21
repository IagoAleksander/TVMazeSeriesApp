package com.iaz.tvmazeseriesapp.util

import androidx.appcompat.widget.SearchView
import java.util.Timer
import java.util.TimerTask

class SearchViewTextListener(
    private val callback: (term: String) -> Unit,
    private val onSubmit: () -> Unit,
    private val considerBlank: Boolean
) : SearchView.OnQueryTextListener {

    private var timer: Timer? = null

    override fun onQueryTextSubmit(text: String?): Boolean {
        timer?.cancel()
        text?.let { callback.invoke(it) }
        onSubmit()
        return true
    }

    override fun onQueryTextChange(text: String?): Boolean {
        timer?.cancel()

        if (text != null) {
            timer = Timer()
            timer?.run {
                this.schedule(
                    object : TimerTask() {
                        override fun run() {
                            if (text.isNotBlank() || considerBlank) callback(text.toString())
                        }
                    },
                    SEARCH_DEBOUNCE_TIME_IN_MILLIS
                )
            }
        }
        return true
    }

    companion object {
        private const val SEARCH_DEBOUNCE_TIME_IN_MILLIS = 250L
    }
}