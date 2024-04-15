package com.example.shopmie.data.repository

import com.example.shopmie.data.local.AppDataBase
import com.example.shopmie.data.repository.interfaces.ISalesRepository

class SalesRepository(private val dataBase: AppDataBase): ISalesRepository {

    override suspend fun getTotalSaleSum(): Double {
        return dataBase.getSalesDao().getAllOrdersSum()
    }
}