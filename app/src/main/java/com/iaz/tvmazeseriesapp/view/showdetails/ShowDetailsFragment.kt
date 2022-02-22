package com.iaz.tvmazeseriesapp.view.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iaz.tvmazeseriesapp.CustomApplication.Companion.prefs
import com.iaz.tvmazeseriesapp.R
import com.iaz.tvmazeseriesapp.databinding.FragmentShowDetailsBinding
import com.iaz.tvmazeseriesapp.viewmodel.ShowDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShowDetailsFragment : Fragment() {
    private val args: ShowDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentShowDetailsBinding
    private lateinit var episodeAdapter: EpisodeAdapter
    private lateinit var showName: String

    private val showDetailsViewModel: ShowDetailsViewModel by viewModel {
        parametersOf(
            args.id,
            args.show
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowDetailsBinding.inflate(LayoutInflater.from(context), container, false)

        setupFavoriteButton()
        initializeObservers()
        setupDropdown()
        setupEpisodesAdapter()

        return binding.root
    }

    private fun setupFavoriteButton() {
        prefs?.sharedPreferenceStringLiveData?.observe(viewLifecycleOwner) { favoritedShowsSet ->
            val isFavorite = favoritedShowsSet.contains(args.id.toString())
            if (isFavorite) {
                binding.button.text = getString(R.string.remove_from_favorites)
                binding.button.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite_border) }
            } else {
                binding.button.text = getString(R.string.add_to_favorites)
                binding.button.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite) }
            }
            binding.button.isChecked = isFavorite
        }

        binding.button.setOnClickListener {
            val isFavorite = prefs?.favoritesPref?.contains(args.id.toString())
            if (isFavorite == true) {
                prefs?.favoritesPref = prefs?.favoritesPref?.minus(args.id.toString()) ?: setOf()
            } else {
                prefs?.favoritesPref = prefs?.favoritesPref?.plus(args.id.toString()) ?: setOf()
            }

        }
    }

    private fun initializeObservers() {
        showDetailsViewModel.show.observe(viewLifecycleOwner) { show ->
            binding.show = show
            showName = show.name
        }
        showDetailsViewModel.episodes.observe(viewLifecycleOwner) { episodes ->
            binding.layoutEpisodes.rvEpisodes.adapter = episodeAdapter
            episodeAdapter.submitList(episodes)
            binding.layoutEpisodes.root.visibility = VISIBLE
        }
    }

    private fun setupDropdown() {
        showDetailsViewModel.seasons.observe(viewLifecycleOwner) { seasons ->
            if (seasons.isNotEmpty()) {
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    seasons.map { String.format("%s %d", getString(R.string.season), it.number) }
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.layoutEpisodes.spSeasons.adapter = adapter
                    showDetailsViewModel.fetchEpisodesBySeasonId(seasons[0].id)
                }
            }
        }
        binding.layoutEpisodes.spSeasons.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedSeason = showDetailsViewModel.seasons.value?.get(position)
                selectedSeason?.let {
                    showDetailsViewModel.fetchEpisodesBySeasonId(it.id)
                }
            }
        }
    }

    private fun setupEpisodesAdapter() {
        episodeAdapter = EpisodeAdapter {
            val action = ShowDetailsFragmentDirections.actionShowDetailsFragmentToEpisodeDetailsFragment(it, showName)
            findNavController().navigate(action)
        }
    }
}