package com.example.shopmie.domain.model

data class OrderUI(
    val id: Int,
    val clientName: String,
    val totalAmount: Double,
    val products: List<OrderItemUI>
)
