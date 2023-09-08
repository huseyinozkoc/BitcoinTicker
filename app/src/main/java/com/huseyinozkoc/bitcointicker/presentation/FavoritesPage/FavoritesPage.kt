package com.huseyinozkoc.bitcointicker.presentation.FavoritesPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.huseyinozkoc.bitcointicker.R
import com.huseyinozkoc.bitcointicker.common.viewBinding
import com.huseyinozkoc.bitcointicker.databinding.FragmentFavoritesPageBinding


class FavoritesPage : Fragment() {

    private val binding by viewBinding(FragmentFavoritesPageBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites_page, container, false)
    }

}