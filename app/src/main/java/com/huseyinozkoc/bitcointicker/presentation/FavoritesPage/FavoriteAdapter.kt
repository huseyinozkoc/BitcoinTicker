package com.huseyinozkoc.bitcointicker.presentation.FavoritesPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.huseyinozkoc.bitcointicker.databinding.ItemFavouriteCoinsBinding
import com.huseyinozkoc.bitcointicker.domain.model.Favorites

class FavoriteAdapter :
    ListAdapter<Favorites, FavoriteAdapter.FavouriteCoinsViewHolder>(FavouritesComparator()) {

    var onDeleteClick: (Favorites) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteCoinsViewHolder {
        val binding =
            ItemFavouriteCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteCoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteCoinsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class FavouriteCoinsViewHolder(private var binding: ItemFavouriteCoinsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Favorites) {

            with(binding) {

                model = item

                root.setOnClickListener {
                    item.coinId?.let { coinId ->
                        val action = FavoritesPageDirections.actionFavoritesPageToDetailPage(coinId)
                        it.findNavController().navigate(action)
                    }
                }

                btnDelete.setOnClickListener {
                    onDeleteClick(item)
                }
            }
        }
    }

    class FavouritesComparator : DiffUtil.ItemCallback<Favorites>() {
        override fun areItemsTheSame(oldItem: Favorites, newItem: Favorites) =
            oldItem.coinId == newItem.coinId

        override fun areContentsTheSame(oldItem: Favorites, newItem: Favorites) =
            oldItem == newItem
    }
}