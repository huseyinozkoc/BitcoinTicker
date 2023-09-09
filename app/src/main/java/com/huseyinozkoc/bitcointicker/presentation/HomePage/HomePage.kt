package com.huseyinozkoc.bitcointicker.presentation.HomePage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.work.WorkInfo
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.*
import com.huseyinozkoc.bitcointicker.databinding.FragmentHomePageBinding
import com.huseyinozkoc.bitcointicker.presentation.MainActivity
import com.huseyinozkoc.bitcointicker.utils.UserManagerDataStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class HomePage : Fragment() {

    private val binding by viewBinding(FragmentHomePageBinding::bind)

    private val homePageViewModel: HomePageViewModel by viewModels()

    private val coinsAdapter by lazy { CoinsAdapter() }

    @Inject
    lateinit var userManager: UserManagerDataStorage

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


            imgSearch.setOnClickListener {
                findNavController().navigate(R.id.action_homePage_to_searchPage)
            }

            imgSignOut.setOnClickListener {
                homePageViewModel.signOut()
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }

        }


    }

    private fun checkData() {

        with(binding) {

            with(homePageViewModel) {

                viewLifecycleOwner.lifecycleScope.launchWhenStarted {

                    currentUser.collect { result ->

                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                tvUsername.text = result.data.email
                                result.data.email?.let { userManager.storeUserEmail(it) }
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }

                    coinMarketsFlow.collect { result ->

                        when (result) {
                            is Resource.Loading -> progressBar.visible()
                            is Resource.Success -> {
                                progressBar.gone()
                                coinsAdapter.submitList(result.data)
                            }
                            is Resource.Error -> {
                                progressBar.gone()
                                //requireView().showSnackbar(result.throwable.message.toString())
                            }
                        }
                    }
                }

                workInfo.observe(viewLifecycleOwner) { listOfWorkInfo ->

                    if (listOfWorkInfo == null || listOfWorkInfo.isEmpty()) {
                        return@observe
                    }

                    val workInfo: WorkInfo = listOfWorkInfo[0]

                    if (workInfo.state == WorkInfo.State.ENQUEUED) {
                        coinMarkets()
                    }
                }

            }
        }
    }


}