package com.iaz.tvmazeseriesapp.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.iaz.tvmazeseriesapp.CustomApplication.Companion.prefs
import com.iaz.tvmazeseriesapp.databinding.FragmentFavoritesBinding
import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.view.GridAdapter
import com.iaz.tvmazeseriesapp.viewmodel.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var gridAdapter: GridAdapter

    private val favoritesViewModel: FavoritesViewModel by viewModel() {
        parametersOf(
            prefs?.favoritesPref?.toList()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)

        setupAdapter()
        setupObservers()

        return binding.root
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
            gridAdapter.submitList(shows)
        }
        prefs?.sharedPreferenceStringLiveData?.observe(viewLifecycleOwner) { favoritedShowsSet ->
            favoritesViewModel.fetchFavorites(favoritedShowsSet.toList())
        }
    }
}