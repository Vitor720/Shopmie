package com.example.shopmie.data.repository

import com.example.shopmie.data.local.OrderDAO
import com.example.shopmie.data.local.ProductDAO
import com.example.shopmie.data.models.OrderData
import com.example.shopmie.data.repository.interfaces.ISalesRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SalesRepositoryTest {

    private lateinit var mockOrderDAO: OrderDAO
    private lateinit var mockProductDAO: ProductDAO

    private lateinit var sut: ISalesRepository

    @Before
    fun start() {
        mockProductDAO = mockkClass(ProductDAO::class)
        mockOrderDAO = mockkClass(OrderDAO::class)
        sut = SalesRepository(mockOrderDAO, mockProductDAO)
    }

    @Test
    fun `get total sale sum test`() = runBlocking {
        val expected = 100.0
        coEvery { mockOrderDAO.getAllOrdersSum() } returns expected

        val result = sut.getTotalSaleSum()

        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `post sale test`() = runBlocking {
        val order = OrderData(1, "Asus", 100.00)
        coEvery { mockOrderDAO.insertOrders(order) } returns Unit
        coEvery { mockProductDAO.insertProducts(*order.products.toTypedArray()) } returns Unit

        sut.postSale(order)

        Truth.assertThat(true).isTrue()
    }

    @Test
    fun `get last order ID test Expected 1`() = runBlocking {
        val expected = 1
        coEvery { mockOrderDAO.getLastOrderID() } returns expected

        val result = sut.getLastOrderID()

        Truth.assertThat(result).isEqualTo(expected)
    }

}