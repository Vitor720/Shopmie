package com.example.shopmie.data.repository.interfaces

import com.example.shopmie.data.models.OrderData

interface ISalesRepository {

    suspend fun getTotalSaleSum(): Double

    suspend fun postSale(order: OrderData)

    suspend fun getLastOrderID(): Int
}