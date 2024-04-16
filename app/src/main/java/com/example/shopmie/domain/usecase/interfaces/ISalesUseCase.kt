package com.example.shopmie.domain.usecase.interfaces

import com.example.shopmie.data.models.SalesProduct
import com.example.shopmie.domain.model.OrderItemUI

interface ISalesUseCase {

    suspend fun postNewSale(
        orderID: Int,
        client: String,
        products: List<OrderItemUI>
    )

    suspend fun getOrderID(): Int
}