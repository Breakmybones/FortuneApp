package com.example.feature_alignment_api.domain.models

data class CardsModel (
    val id: Long,
    val dignity: String,
    val description: String,
    val lasso: String,
    val suit: String?,
    val name: String?
)
