package com.example.shopmie.domain.model

data class OrderItemUI(
    val name: String,
    val quantity: Int,
    val price: Double,
) {
    val totalItemAmount: Double
        get() = price * quantity
}