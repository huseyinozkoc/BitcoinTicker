package com.huseyinozkoc.bitcointicker.presentation.DetailPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.*
import com.huseyinozkoc.bitcointicker.databinding.FragmentDetailPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.Duration.Companion.milliseconds


@AndroidEntryPoint
class DetailPage : Fragment() {

    private val binding by viewBinding(FragmentDetailPageBinding::bind)

    private val detailViewModel: DetailPageViewModel by viewModels()

    private val secondList = listOf("10 sec", "15 sec", "30 sec", "60 sec", "90 sec")
    private val milliSecondList = listOf(10000, 15000, 30000, 60000, 90000)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        checkData()

    }


    private fun initUI() {

        val secondsAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_menu, secondList)

        with(binding) {

            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }

            btnFavourite.setOnClickListener {
                detailViewModel.addToFavourites()
            }

            actSecond.setAdapter(secondsAdapter)

            actSecond.setOnItemClickListener { _, _, position, _ ->
                detailViewModel.currentPriceById(milliSecondList[position].milliseconds)
            }
        }
    }

    private fun checkData() {

        with(binding) {

            with(detailViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    coinDetailFlow.collect { result ->

                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                model = result.data
                                currentPrice = result.data.currentPrice
                            }

                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    addToFavourite.collect { result ->
                        when (result) {
                            is UIScreen.SnackBar -> requireView().showSnackbar(result.message)
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    currentPriceFlow.collect {
                        currentPrice = it
                    }
                }
            }
        }
    }


}