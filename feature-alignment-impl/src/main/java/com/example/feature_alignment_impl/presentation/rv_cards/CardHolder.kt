package com.example.feature_alignment_impl.presentation.rv_cards

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_api.domain.models.CardsModel
import com.example.feature_alignment_impl.R
import com.example.feature_alignment_impl.databinding.ItemCardsBinding

class CardHolder(
    private val binding: ItemCardsBinding,
    private val onItemClick: (Long) -> Boolean
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(card: CardsModel){
        with(binding){
            iv.setImageResource(R.drawable.card)
            root.setOnClickListener {
                val res = onItemClick(card.id)
                if(res){
                    iv.alpha = 0.5F
                }else{
                    iv.alpha = 1F
                }
            }
        }
    }
}