package com.huseyinozkoc.bitcointicker.presentation.HomePage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.Resource
import com.huseyinozkoc.bitcointicker.common.gone
import com.huseyinozkoc.bitcointicker.common.viewBinding
import com.huseyinozkoc.bitcointicker.common.visible
import com.huseyinozkoc.bitcointicker.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class HomePage : Fragment() {

    private val binding by viewBinding(FragmentHomePageBinding::bind)

    private val homePageViewModel: HomePageViewModel by viewModels()

    private val coinsAdapter by lazy { CoinsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        checkData()

    }

    private fun initUI() {
        with(binding) {
            recyclerView.adapter = coinsAdapter
        }
    }

    private fun checkData() {

        with(binding) {

            with(homePageViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    currentUser.collect { result ->
                        when (result) {

                            is Resource.Loading -> {}
                            is Resource.Success -> {Log.d("Home Page User Success", result.data.email.toString())}
                            is Resource.Error -> { Log.d("Home Page User Success", result.throwable.message.toString())}

                        }

                    }

                    coinMarketsFlow.collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                Log.d("Home Page Loading", result.toString())
                                progressBar.visible()
                            }
                            is Resource.Success -> {
                                Log.d("Home Page Success", result.data.toString())
                                progressBar.gone()
                                coinsAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                Log.d("Home Page Error", result.throwable.message.toString())
                                progressBar.gone()
                            }

                        }

                    }


                }


            }

        }


    }


}