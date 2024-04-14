package com.example.shopmie.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
class OrderData (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var clientName: String,
    var totalAmount: Double,
){
    @Ignore
    var products: List<SalesProduct> = listOf()
}