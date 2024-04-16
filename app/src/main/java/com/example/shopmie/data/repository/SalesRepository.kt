package com.example.shopmie.data.repository

import com.example.shopmie.data.local.OrderDAO
import com.example.shopmie.data.local.ProductDAO
import com.example.shopmie.data.models.OrderData
import com.example.shopmie.data.repository.interfaces.ISalesRepository

class SalesRepository(
    private val orderDAO: OrderDAO,
    private val productDAO: ProductDAO,
): ISalesRepository {

    override suspend fun getTotalSaleSum(): Double {
        return orderDAO.getAllOrdersSum()
    }

    override suspend fun postSale(order: OrderData) {
        orderDAO.insertOrders(order)
        productDAO.insertProducts(*order.products.toTypedArray())
    }

    override suspend fun getLastOrderID(): Int {
        return orderDAO.getLastOrderID()
    }

}