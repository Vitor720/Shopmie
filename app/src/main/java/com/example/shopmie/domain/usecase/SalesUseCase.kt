package com.example.shopmie.domain.usecase

import com.example.shopmie.data.models.OrderData
import com.example.shopmie.data.models.SalesProduct
import com.example.shopmie.data.repository.interfaces.ISalesRepository
import com.example.shopmie.domain.model.OrderItemUI
import com.example.shopmie.domain.usecase.interfaces.ISalesUseCase

class SalesUseCase(private val repo: ISalesRepository): ISalesUseCase {

    override suspend fun postNewSale(orderID: Int, client: String, products: List<OrderItemUI>) {
        val order = OrderData(orderID, client, products.sumOf { it.totalItemAmount}).apply {
            this.products = products.map {
                SalesProduct(
                    it.name,
                    it.quantity,
                    it.price,
                    orderID
                )
            }
        }
        repo.postSale(order)
    }

    override suspend fun getOrderID(): Int {
        var currentOrderID = repo.getLastOrderID()
        currentOrderID++
        return currentOrderID
    }
}