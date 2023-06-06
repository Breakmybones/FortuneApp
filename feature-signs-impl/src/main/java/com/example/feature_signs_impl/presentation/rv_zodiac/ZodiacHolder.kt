package com.example.feature_signs_impl.presentation.rv_zodiac

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_signs_api.model.ZodiacInfo
import com.example.feature_signs_impl.databinding.ItemZodiacBinding

class ZodiacHolder(
    private val binding: ItemZodiacBinding,
    private val isLove: Boolean,
    private val onItemClick: (String) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: ZodiacInfo){
        with(binding){
            root.setOnClickListener {
                onItemClick(item.name)
            }
            name.text = item.name
            if(isLove) {
                percent.text = "${item.love} %"
            }else{
                percent.text= "${item.friendship} %"
            }
        }
    }
}