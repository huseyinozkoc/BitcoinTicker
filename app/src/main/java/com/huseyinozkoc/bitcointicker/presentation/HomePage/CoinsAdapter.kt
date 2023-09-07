package com.huseyinozkoc.bitcointicker.presentation.HomePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huseyinozkoc.bitcointicker.databinding.ItemCoinBinding
import com.huseyinozkoc.bitcointicker.domain.model.CoinMarkets

class CoinsAdapter : ListAdapter<CoinMarkets, CoinsAdapter.CoinsViewHolder>(CoinComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class CoinsViewHolder(private var binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CoinMarkets) {

            with(binding) {

                model = item

                root.setOnClickListener {
                    item.coinId?.let { coinId ->

                        val action = HomePageDirections.actionHomePageToDetailPage(coinId)
                        it.findNavController().navigate(action)
                    }
                }
            }
        }
    }

    class CoinComparator : DiffUtil.ItemCallback<CoinMarkets>() {
        override fun areItemsTheSame(oldItem: CoinMarkets, newItem: CoinMarkets) =
            oldItem.coinId == newItem.coinId

        override fun areContentsTheSame(oldItem: CoinMarkets, newItem: CoinMarkets) =
            oldItem == newItem
    }
}