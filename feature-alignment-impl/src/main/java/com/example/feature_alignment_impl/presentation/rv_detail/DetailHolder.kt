package com.example.feature_alignment_impl.presentation.rv_detail

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_api.domain.models.CardsModel
import com.example.feature_alignment_impl.R
import com.example.feature_alignment_impl.databinding.ItemCardsBinding

class DetailHolder(
    private val binding: ItemCardsBinding,
    private val onItemClick: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(card: CardsModel){
        with(binding){
            iv.setImageResource(R.drawable.card)
            root.setOnClickListener {
                onItemClick(card.id)
            }
        }
    }
}