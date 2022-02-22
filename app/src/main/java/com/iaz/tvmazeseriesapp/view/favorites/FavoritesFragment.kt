package com.iaz.tvmazeseriesapp.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.iaz.tvmazeseriesapp.CustomApplication.Companion.prefs
import com.iaz.tvmazeseriesapp.R
import com.iaz.tvmazeseriesapp.databinding.FragmentFavoritesBinding
import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.util.SearchViewTextListener
import com.iaz.tvmazeseriesapp.util.hideSoftKeyboard
import com.iaz.tvmazeseriesapp.view.GridAdapter
import com.iaz.tvmazeseriesapp.viewmodel.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.Locale

class FavoritesFragment : Fragment() {
    private val filterViewTextListener: SearchViewTextListener = SearchViewTextListener(
        onSubmit = { hideSoftKeyboard() },
        callback = { term -> filterData(term) },
        considerBlank = true
    )

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var gridAdapter: GridAdapter

    private val favoritesViewModel: FavoritesViewModel by viewModel {
        parametersOf(
            prefs?.favoritesPref?.toList()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)

        setupFilterView()
        setupAdapter()
        setupObservers()

        return binding.root
    }

    private fun setupFilterView() {
        binding.svFavorites.setOnQueryTextListener(filterViewTextListener)

        val searchIcon = binding.svFavorites.findViewById(androidx.appcompat.R.id.search_mag_icon) as ImageView
        context?.let { ContextCompat.getColor(it, R.color.accent) }?.let {
            searchIcon.setColorFilter(
                it,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
        val closeIcon = binding.svFavorites.findViewById(androidx.appcompat.R.id.search_close_btn) as ImageView
        context?.let { ContextCompat.getColor(it, R.color.accent) }?.let {
            closeIcon.setColorFilter(
                it,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
        closeIcon.setOnClickListener {
            binding.svFavorites.setQuery("", false)
            gridAdapter.submitList(favoritesViewModel.shows.value)
            hideSoftKeyboard()
        }

        binding.svFavorites.isFocusable = false
        binding.svFavorites.isIconified = false
        binding.svFavorites.clearFocus()
    }

    private fun setupAdapter() {
        gridAdapter = GridAdapter {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToShowDetailsFragment(it.id, it as Show)
            findNavController().navigate(action)
        }
        binding.rvFavorite.adapter = gridAdapter
    }

    private fun setupObservers() {
        favoritesViewModel.shows.observe(viewLifecycleOwner) { shows ->
            checkEmptyStateAndShowData(shows)
        }
        prefs?.sharedPreferenceStringLiveData?.observe(viewLifecycleOwner) { favoritedShowsSet ->
            favoritesViewModel.fetchFavorites(favoritedShowsSet.toList())
        }
    }

    private fun filterData(term: String) {
        val filteredList = favoritesViewModel.shows.value?.filter {
            it.name.lowercase(Locale.getDefault()).contains(term.lowercase(Locale.getDefault()))
        }
        checkEmptyStateAndShowData(filteredList)
    }

    private fun checkEmptyStateAndShowData(shows: List<Show>?) {
        if (shows.isNullOrEmpty()) {
            binding.rvFavorite.visibility = View.GONE
            binding.tvEmptyStateFavorites.visibility = View.VISIBLE
        } else {
            gridAdapter.submitList(shows.sortedBy { it.name })
            binding.rvFavorite.visibility = View.VISIBLE
            binding.tvEmptyStateFavorites.visibility = View.GONE
        }
    }
}