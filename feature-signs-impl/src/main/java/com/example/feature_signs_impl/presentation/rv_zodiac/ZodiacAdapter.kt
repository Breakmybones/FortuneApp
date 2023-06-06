package com.example.feature_signs_impl.presentation.rv_zodiac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_signs_api.model.ListResponse
import com.example.feature_signs_api.model.ZodiacInfo
import com.example.feature_signs_impl.databinding.ItemZodiacBinding

class ZodiacAdapter(private var listModel: List<ZodiacInfo>,
                    private var isLove: Boolean,
                    private val onItemClick:(String) -> (Unit)) : RecyclerView.Adapter<ZodiacHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacHolder = ZodiacHolder(
        binding = ItemZodiacBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        isLove = isLove,
        onItemClick = onItemClick
    )

    override fun getItemCount(): Int {
        return listModel.size
    }

    override fun onBindViewHolder(holder: ZodiacHolder, position: Int) {
        holder.onBind(listModel[position])
    }
}