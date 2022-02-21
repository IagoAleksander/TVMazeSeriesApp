package com.iaz.tvmazeseriesapp.view.persondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.iaz.tvmazeseriesapp.databinding.FragmentPersonDetailsBinding
import com.iaz.tvmazeseriesapp.viewmodel.PersonDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PersonDetailsFragment : Fragment() {
    private val args: PersonDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentPersonDetailsBinding
    private lateinit var showBasicAdapter: ShowBasicAdapter

    private val personDetailsViewModel: PersonDetailsViewModel by viewModel() {
        parametersOf(
            args.person.id
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonDetailsBinding.inflate(LayoutInflater.from(context), container, false)
        binding.person = args.person

        setupShowBasicAdapter()
        setupObservers()

        return binding.root
    }

    private fun setupShowBasicAdapter() {
        showBasicAdapter = ShowBasicAdapter {
            val action = PersonDetailsFragmentDirections.actionPersonDetailsFragmentToShowDetailsFragment(it.id, null)
            findNavController().navigate(action)
        }
        binding.rvShows.adapter = showBasicAdapter
    }

    private fun setupObservers() {
        personDetailsViewModel.castCredits.observe(viewLifecycleOwner) { result ->
            if (result.isEmpty().not()) {
                showBasicAdapter.submitList(result.map { it._embedded.show })
                binding.tvShowParticipatedInLabel.visibility = VISIBLE
            }
        }
    }
}