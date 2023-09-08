package com.huseyinozkoc.bitcointicker.presentation.SearchPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huseyinozkoc.bitcointicker.databinding.ItemSearchCoinBinding
import com.huseyinozkoc.bitcointicker.domain.model.CoinList

class SearchCoinAdapter :
    ListAdapter<CoinList, SearchCoinAdapter.CoinViewHolder>(CoinComparator()) {

    var onCoinClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding =
            ItemSearchCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchCoinAdapter.CoinViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class CoinViewHolder(private var binding: ItemSearchCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CoinList) {

            with(binding) {

                tvTitle.text = item.name

                root.setOnClickListener {
                    item.coinId?.let { coinId ->
                        onCoinClick(coinId)
                    }
                }
            }
        }
    }

    class CoinComparator : DiffUtil.ItemCallback<CoinList>() {
        override fun areItemsTheSame(oldItem: CoinList, newItem: CoinList) =
            oldItem.coinId == newItem.coinId

        override fun areContentsTheSame(oldItem: CoinList, newItem: CoinList) =
            oldItem == newItem
    }
}