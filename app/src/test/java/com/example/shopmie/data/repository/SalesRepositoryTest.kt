package com.example.shopmie.data.repository

import com.example.shopmie.data.local.AppDataBase
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

    private lateinit var mockDataBase: AppDataBase

    private lateinit var sut: ISalesRepository

    @Before
    fun start() {
        mockDataBase = mockkClass(AppDataBase::class)
        sut = SalesRepository(mockDataBase)
    }

    @Test
    fun `get total sale sum test`() = runBlocking {
        val expected = 100.0
        coEvery { mockDataBase.getSalesDao().getAllOrdersSum() } returns expected

        val result = sut.getTotalSaleSum()

        Truth.assertThat(result).isEqualTo(expected)
    }

}