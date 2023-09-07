package com.huseyinozkoc.bitcointicker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.viewBinding
import com.huseyinozkoc.bitcointicker.databinding.FragmentSearchPageBinding


class SearchPage : Fragment() {

    private val binding by viewBinding(FragmentSearchPageBinding::bind)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_page, container, false)
    }

}