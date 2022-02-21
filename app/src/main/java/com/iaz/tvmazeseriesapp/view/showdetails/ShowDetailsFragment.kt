package com.iaz.tvmazeseriesapp.view.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

    private val showDetailsViewModel: ShowDetailsViewModel by viewModel() {
        parametersOf(
            args.id
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowDetailsBinding.inflate(LayoutInflater.from(context), container, false)

        initializeObservers()
        setupDropdown()
        setupEpisodesAdapter()

        return binding.root
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