package com.huseyinozkoc.bitcointicker.presentation.FavoritesPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.*
import com.huseyinozkoc.bitcointicker.databinding.FragmentFavoritesPageBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesPage : Fragment() {

    private val binding by viewBinding(FragmentFavoritesPageBinding::bind)

    private val favouritesViewModel: FavoritesViewModel by viewModels()

    private val favouriteAdapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        checkData()

    }


    private fun initUI() {

        with(binding) {

            rvFavourites.adapter = favouriteAdapter

            favouriteAdapter.onDeleteClick = { favouritesViewModel.deleteFromFavourites(it) }
        }
    }

    private fun checkData() {

        with(binding) {

            with(favouritesViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    favouritesFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                favouriteAdapter.submitList(result.data)
                            }

                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    deleteFromFavouritesFlow.collect { result ->
                        when (result) {
                            is UIScreen.SnackBar -> requireView().showSnackbar(result.message)
                        }
                    }
                }
            }
        }
    }




}