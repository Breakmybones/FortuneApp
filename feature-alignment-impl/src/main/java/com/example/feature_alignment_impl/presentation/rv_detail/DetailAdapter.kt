package com.example.feature_alignment_impl.presentation.rv_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_api.domain.models.CardsListModel
import com.example.feature_alignment_impl.databinding.ItemCardsBinding

class DetailAdapter(private var listModel: CardsListModel,
                  private val onItemClick:(Long) -> (Unit))
    : RecyclerView.Adapter<DetailHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder = DetailHolder(
        binding = ItemCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick = onItemClick
    )

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.onBind(listModel.list[position])
    }

    override fun getItemCount(): Int = listModel.list.size
}