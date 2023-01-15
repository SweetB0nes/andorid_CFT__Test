package com.example.cft_test.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cft_test.databinding.ItemHistoryBinding
import com.example.cft_test.domain.entity.BinHistory
import com.example.cft_test.presentation.diffUtils.BinItemHistoryDiffCallback
import com.example.cft_test.presentation.viewHolders.BinHistoryViewHolder

class BinHistoryAdapter :
    ListAdapter<BinHistory, BinHistoryViewHolder>(BinItemHistoryDiffCallback()) {

    var clickListener: ((BinHistory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return BinHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinHistoryViewHolder, position: Int) {
        val item = getItem(position)
        setupClickListeners(holder, item)
        setupViews(holder, item)
    }

    private fun setupViews(holder: BinHistoryViewHolder, item: BinHistory) {
        holder.binding.historyBinTv.text = item.id
    }

    private fun setupClickListeners(holder: BinHistoryViewHolder, binHistory: BinHistory) {
        holder.binding.itemConstraintLayout.setOnClickListener {
            clickListener?.invoke(binHistory)
        }
    }
}