package com.bitpanda.livechallenge.ui.coins

import androidx.recyclerview.widget.RecyclerView
import com.bitpanda.livechallenge.databinding.RowRecyclerCoinBinding

class CoinsViewHolder(
    private val binding: RowRecyclerCoinBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(item: CoinUIModel) = with(binding) {
        coinName.text = item.name
        coinSymbol.text = item.symbol
        coinPrice.text = item.price
    }
}