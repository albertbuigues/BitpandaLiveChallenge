package com.bitpanda.livechallenge.ui.coins

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bitpanda.livechallenge.R
import com.bitpanda.livechallenge.databinding.FragmentCoinsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsFragment : Fragment(R.layout.fragment_coins) {

    private lateinit var binding: FragmentCoinsBinding

    private val adapter = CoinsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCoinsBinding.bind(view)

        binding.recyclerView.adapter = adapter

    }

    private fun renderList(items: List<CoinUIModel>) {
        adapter.submitList(items)
    }
}