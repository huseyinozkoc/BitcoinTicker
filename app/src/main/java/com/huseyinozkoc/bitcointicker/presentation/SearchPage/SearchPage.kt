package com.huseyinozkoc.bitcointicker.presentation.SearchPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.*
import com.huseyinozkoc.bitcointicker.databinding.FragmentSearchPageBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchPage : Fragment() {

    private val binding by viewBinding(FragmentSearchPageBinding::bind)

    private val searchCoinViewModel: SearchPageViewModel by viewModels()

    private val searchCoinAdapter by lazy { SearchCoinAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        collectData()

    }


    private fun initUI() {

        with(binding) {

            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }

            rvSearchCoin.adapter = searchCoinAdapter

            searchCoinAdapter.onCoinClick = {
                val action = SearchPageDirections.actionSearchPageToDetailPage(it)
                findNavController().navigate(action)
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        searchCoinViewModel.searchCoin(it)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun collectData() {

        with(binding) {

            with(searchCoinViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    coinList.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                searchCoinAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    coinListFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                searchCoinAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }
            }
        }
    }


}