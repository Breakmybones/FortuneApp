package com.example.feature_alignment_impl.presentation.rv_alignment

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_alignment_impl.databinding.ItemAlignmentBinding

class AlignmentHolder(
    private val binding: ItemAlignmentBinding,
    private val onItemClick: (String, String) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    fun onBind(alignment: ItemModel){
        with(binding){
            txt.text = alignment.name
            iv.setImageResource(alignment.picture)
            root.setOnClickListener {
               onItemClick(alignment.name, alignment.description)
            }
        }
    }

}
