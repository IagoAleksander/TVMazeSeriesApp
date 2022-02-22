package com.iaz.tvmazeseriesapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.navigation.fragment.findNavController
import com.iaz.tvmazeseriesapp.R
import com.iaz.tvmazeseriesapp.databinding.FragmentHomeBinding
import com.iaz.tvmazeseriesapp.repository.model.GridItem
import com.iaz.tvmazeseriesapp.repository.model.Person
import com.iaz.tvmazeseriesapp.repository.model.Show
import com.iaz.tvmazeseriesapp.util.SearchViewTextListener
import com.iaz.tvmazeseriesapp.util.hideSoftKeyboard
import com.iaz.tvmazeseriesapp.view.GridAdapter
import com.iaz.tvmazeseriesapp.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val searchViewTextListener: SearchViewTextListener = SearchViewTextListener(
        onSubmit = { hideSoftKeyboard() },
        callback = { term -> fetchData(term) },
        considerBlank = false
    )

    private lateinit var binding: FragmentHomeBinding
    private lateinit var showPaginatedAdapter: ShowPaginatedAdapter
    private lateinit var gridAdapter: GridAdapter

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setupSearchView()
        initializeListeners()
        setupPagingAdapter()
        setupSearchAdapter()
        setupObservers()

        return binding.root
    }

    private fun setupSearchView() {
        val searchIcon = binding.svLayout.svHome.findViewById(androidx.appcompat.R.id.search_mag_icon) as ImageView
        context?.let { ContextCompat.getColor(it, R.color.accent) }?.let {
            searchIcon.setColorFilter(
                it,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
        val closeIcon = binding.svLayout.svHome.findViewById(androidx.appcompat.R.id.search_close_btn) as ImageView
        context?.let { ContextCompat.getColor(it, R.color.accent) }?.let {
            closeIcon.setColorFilter(
                it,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
        closeIcon.setOnClickListener {
            binding.svLayout.svHome.setQuery("", false)
            binding.rvHome.adapter = showPaginatedAdapter
            binding.rvHome.visibility = VISIBLE
            binding.tvEmptyStateHome.visibility = GONE
            hideSoftKeyboard()
        }

        binding.svLayout.svHome.isFocusable = false
        binding.svLayout.svHome.isIconified = false
        binding.svLayout.svHome.clearFocus()
    }

    private fun initializeListeners() {
        binding.svLayout.svHome.setOnQueryTextListener(searchViewTextListener)

        binding.svLayout.rgSearch.setOnCheckedChangeListener { _, _ ->
            fetchData(binding.svLayout.svHome.query.toString())
        }
    }

    private fun setupPagingAdapter() {
        showPaginatedAdapter = ShowPaginatedAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToShowDetailsFragment(it.id, it)
            findNavController().navigate(action)
        }

        binding.rvHome.adapter = showPaginatedAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            whenCreated {
                homeViewModel.flowShows.collectLatest { pagingData ->
                    binding.rvHome.visibility = VISIBLE
                    showPaginatedAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun setupSearchAdapter() {
        gridAdapter = GridAdapter {
            val action = if (binding.svLayout.rdShows.isChecked) {
                HomeFragmentDirections.actionHomeFragmentToShowDetailsFragment(it.id, it as Show)
            } else {
                HomeFragmentDirections.actionHomeFragmentToPersonDetailsFragment(it as Person)
            }
            findNavController().navigate(action)
        }
    }

    private fun setupObservers() {
        homeViewModel.shows.observe(viewLifecycleOwner) { result ->
            checkEmptyStateAndShowData(result)
        }
        homeViewModel.people.observe(viewLifecycleOwner) { result ->
            checkEmptyStateAndShowData(result)
        }
    }

    private fun fetchData(term: String) {
        if (binding.svLayout.rdShows.isChecked) {
            homeViewModel.fetchShows(term)
        } else {
            homeViewModel.fetchPeople(term)
        }
    }

    private fun checkEmptyStateAndShowData(shows: List<GridItem>?) {
        if (shows.isNullOrEmpty()) {
            binding.rvHome.visibility = GONE
            binding.tvEmptyStateHome.visibility = VISIBLE
        } else {
            binding.rvHome.adapter = gridAdapter
            gridAdapter.submitList(shows)
            binding.rvHome.visibility = VISIBLE
            binding.tvEmptyStateHome.visibility = GONE
        }
    }
}