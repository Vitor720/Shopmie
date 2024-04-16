package com.example.shopmie.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_products",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = OrderData::class,
            parentColumns = ["id"],
            childColumns = ["orderID"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
    )
data class SalesProduct(
    val name: String,
    val quantity: Int,
    val price: Double,
    val orderID: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    val totalItemAmount: Double
        get() = price * quantity
}