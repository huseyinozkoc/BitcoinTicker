package com.huseyinozkoc.bitcointicker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.viewBinding
import com.huseyinozkoc.bitcointicker.databinding.FragmentSplashPageBinding


class SplashPage : Fragment() {


    private val binding by viewBinding(FragmentSplashPageBinding::bind)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}