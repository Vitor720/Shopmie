package com.example.shopmie.ui.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.shopmie.data.repository.SalesRepository
import com.example.shopmie.data.repository.interfaces.ISalesRepository
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class HomeViewModelTest {

    private lateinit var repo: ISalesRepository
    private val salesAmountSequence = mutableListOf<Double>()

    private lateinit var sut: HomeViewModel
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()

//    @get:Rule
//    @ExperimentalCoroutinesApi
//    val coroutineRule = CoroutinesTestRule()


    @Before
    fun start() {
        repo = mockkClass(ISalesRepository::class, relaxed = true)

        sut = HomeViewModel(repo).apply {
            salesAmount.observeForever { salesAmountSequence.add(it) }
        }
        salesAmountSequence.clear()
    }

//    @Test
//    fun testWithNetwork() {
//        Mockito.`when`(repo.()).thenReturn(Single.just(listOf<TrendingRepo>()))
//        trendingViewModel.fetchTrendingRepos()
//        verify(trendingRepository, times(1)).getTrendingRepos()
//    }
//
    @Test
    fun `getTotalSales Success Expected 250`(): Unit = runBlocking{
        val expected = 250.0

        coEvery { repo.getTotalSaleSum() }.returns(expected)

        sut.getTotalSales()


        assertThat(salesAmountSequence).isEqualTo(listOf(expected))
        coVerify(exactly = 1) { repo.getTotalSaleSum() }
    }

}