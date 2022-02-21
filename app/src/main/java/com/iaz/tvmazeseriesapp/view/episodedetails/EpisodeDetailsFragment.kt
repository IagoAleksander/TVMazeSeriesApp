package com.iaz.tvmazeseriesapp.view.episodedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.iaz.tvmazeseriesapp.databinding.FragmentEpisodeDetailsBinding

class EpisodeDetailsFragment : Fragment() {
    private val args: EpisodeDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentEpisodeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(LayoutInflater.from(context), container, false)

        binding.episode = args.episode
        binding.tvShowTitle.text = args.showName

        return binding.root
    }
}