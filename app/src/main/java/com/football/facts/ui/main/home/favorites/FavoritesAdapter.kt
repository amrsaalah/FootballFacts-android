package com.football.facts.ui.main.home.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.football.facts.databinding.ListItemFavoriteBinding

class FavoritesAdapter(
    private val viewModel: FavoritesViewModel
) : ListAdapter<FavoriteDisplayItem, FavoritesAdapter.CountryHolder>(FavoriteDiffUtils()) {

    inner class CountryHolder(
        private val binding: ListItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavoriteDisplayItem) {
            binding.txt.text = item.name
            binding.img.load(item.icon)
            binding.root.setOnClickListener {
                viewModel.onFavoriteClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(
            ListItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoriteDiffUtils : DiffUtil.ItemCallback<FavoriteDisplayItem>() {
        override fun areItemsTheSame(
            oldItem: FavoriteDisplayItem,
            newItem: FavoriteDisplayItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavoriteDisplayItem,
            newItem: FavoriteDisplayItem
        ): Boolean {
            return oldItem == newItem
        }
    }

}