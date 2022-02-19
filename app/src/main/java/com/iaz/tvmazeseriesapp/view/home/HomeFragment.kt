package com.iaz.tvmazeseriesapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.paging.PagingData
import com.iaz.tvmazeseriesapp.databinding.FragmentHomeBinding
import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.view.ShowAdapter
import com.iaz.tvmazeseriesapp.viewmodel.HomeShowViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var showAdapter: ShowAdapter

    private val homeViewModel: HomeShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setupAdapter()

        return binding.root
    }

    private fun setupAdapter() {
        showAdapter = ShowAdapter {}

        binding.rvShow.adapter = showAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            whenCreated {
                homeViewModel.flowShows.collectLatest {
                        pagingData -> showAdapter.submitData(pagingData)
                }
            }
        }

    }
}