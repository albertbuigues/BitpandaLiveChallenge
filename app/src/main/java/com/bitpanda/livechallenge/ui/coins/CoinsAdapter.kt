package com.bitpanda.livechallenge.ui.coins

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bitpanda.livechallenge.databinding.RowRecyclerCoinBinding

class CoinsAdapter : ListAdapter<CoinUIModel, CoinsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val context = LayoutInflater.from(parent.context)
        val binding = RowRecyclerCoinBinding.inflate(context, parent, false)
        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoinUIModel>() {
            override fun areItemsTheSame(oldItem: CoinUIModel, newItem: CoinUIModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CoinUIModel, newItem: CoinUIModel): Boolean =
                oldItem == newItem
        }
    }
}