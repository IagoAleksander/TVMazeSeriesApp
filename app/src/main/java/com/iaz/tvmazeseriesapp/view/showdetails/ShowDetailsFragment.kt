package com.iaz.tvmazeseriesapp.view.showdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.iaz.tvmazeseriesapp.R
import com.iaz.tvmazeseriesapp.databinding.FragmentShowDetailsBinding
import com.iaz.tvmazeseriesapp.viewmodel.ShowDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShowDetailsFragment : Fragment() {
    private val args: ShowDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentShowDetailsBinding

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

        return binding.root
    }

    private fun initializeObservers() {
        showDetailsViewModel.show.observe(viewLifecycleOwner) { show ->
            binding.show = show
        }
    }
}