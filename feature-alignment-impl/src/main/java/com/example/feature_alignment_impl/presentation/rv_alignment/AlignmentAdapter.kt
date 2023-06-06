package com.example.feature_alignment_impl.presentation.rv_alignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_impl.databinding.ItemAlignmentBinding

class AlignmentAdapter(private var listModel: List<ItemModel>,
                       private val onItemClick:(String, String) -> (Unit)) :
    RecyclerView.Adapter<AlignmentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlignmentHolder {
        return AlignmentHolder(
            binding = ItemAlignmentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick = onItemClick
        )
    }

    override fun getItemCount() = listModel.size

    override fun onBindViewHolder(holder: AlignmentHolder, position: Int) {
        holder.onBind(listModel[position])
    }
}