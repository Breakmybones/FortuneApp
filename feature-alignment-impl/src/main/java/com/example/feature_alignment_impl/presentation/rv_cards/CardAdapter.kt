package com.example.feature_alignment_impl.presentation.rv_cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_api.domain.models.CardsListModel
import com.example.feature_alignment_impl.databinding.ItemCardsBinding


class CardAdapter(private var listModel: CardsListModel,
                  private val onItemClick:(Long) -> (Boolean))
    : RecyclerView.Adapter<CardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder = CardHolder(
        binding = ItemCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick = onItemClick
    )

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.onBind(listModel.list[position])
    }

    override fun getItemCount(): Int = listModel.list.size
}