package com.example.shopmie.data.repository.interfaces


interface ISalesRepository {

    suspend fun getTotalSaleSum(): Double

}