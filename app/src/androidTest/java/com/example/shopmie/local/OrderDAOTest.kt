package com.example.shopmie.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shopmie.data.local.AppDataBase
import com.example.shopmie.data.local.OrderDAO
import com.example.shopmie.data.models.OrderData
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrderDAOTest {

    private lateinit var sut: OrderDAO

    @Before
    fun start() {
       val db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).build()

        sut = db.getSalesDao()
    }

    @Test
    fun getOrdersValueSumExpected300(): Unit   {
        val firstOrder = OrderData(1, "Asus", 100.00)
        val secondOrder = OrderData(2, "Dell", 200.00)
        sut.insertOrders(firstOrder, secondOrder)
        val result = sut.getAllOrdersSum()
        Assert.assertEquals(300.00, result, 0.00001)
    }

    @Test
    fun getOrdersValueSumExpectedZero() {
        val result = sut.getAllOrdersSum()
        Assert.assertEquals(0.00, result, 0.00001)
    }

    @Test
    fun getLastOrderIDExpected2() {
        val firstOrder = OrderData(1, "Asus", 100.00)
        val secondOrder = OrderData(2, "Dell", 200.00)
        sut.insertOrders(firstOrder, secondOrder)
        val result = sut.getLastOrderID()
        Assert.assertEquals(2, result)
    }

    @Test
    fun getLastOrderIDExpectedZero() {
        val result = sut.getLastOrderID()
        Assert.assertEquals(0, result)
    }
}