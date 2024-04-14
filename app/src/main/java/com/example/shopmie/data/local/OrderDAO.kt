package com.example.shopmie.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shopmie.data.models.OrderData

@Dao
interface OrderDAO {

    @Query("SELECT sum(totalAmount) FROM orders")
    fun getAllOrders(): Double

    @Insert
    fun insertOrders(vararg orders: OrderData)

    @Delete
    fun deleteOrder(order: OrderData)

    @Query("SELECT max(id) FROM orders")
    fun getLastOrderID(): Int
}