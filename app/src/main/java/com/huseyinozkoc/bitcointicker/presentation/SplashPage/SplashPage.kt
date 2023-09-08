package com.huseyinozkoc.bitcointicker.presentation.SplashPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.viewBinding
import com.huseyinozkoc.bitcointicker.databinding.FragmentSplashPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashPage : Fragment() {


    private val binding by viewBinding(FragmentSplashPageBinding::bind)

    @Inject
     lateinit var analytics: FirebaseAnalytics

    @Inject
    lateinit var crashlytics: FirebaseCrashlytics


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        analytics.setAnalyticsCollectionEnabled(true)




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        CoroutineScope(Dispatchers.Main).launch {

            delay(5000)
            findNavController().navigate(R.id.action_splashPage_to_signInPage)
            //crashlytics.recordException(throw RuntimeException("Test Crash"))
        }


    }

}