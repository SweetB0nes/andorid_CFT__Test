package com.example.cft_test.presentation.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.cft_test.domain.entity.DescriptionContent

class DescriptionContentDiffCallback: DiffUtil.ItemCallback<DescriptionContent>() {
    override fun areItemsTheSame(
        oldItem: DescriptionContent,
        newItem: DescriptionContent
    ): Boolean {
        return oldItem.description == newItem.description
    }

    override fun areContentsTheSame(
        oldItem: DescriptionContent,
        newItem: DescriptionContent
    ): Boolean {
        return oldItem == newItem
    }
}