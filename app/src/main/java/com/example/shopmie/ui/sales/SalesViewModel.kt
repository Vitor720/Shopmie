package com.example.shopmie.ui.sales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmie.domain.model.OrderItemUI
import com.example.shopmie.domain.usecase.interfaces.ISalesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SalesViewModel(private val useCase: ISalesUseCase): ViewModel() {

    private val orderProducts = MutableLiveData<List<OrderItemUI>>()
    val products = orderProducts as LiveData<List<OrderItemUI>>

    private val currentOrderID = MutableLiveData<Int>()
    val orderID = currentOrderID as LiveData<Int>

    private val totalOrderValue = MutableLiveData<Double>()
    val orderValue = totalOrderValue as LiveData<Double>



    fun addProduct(name: String?, quantity: Int?, price: Double?) {
        val items = orderProducts.value?.toMutableList() ?: mutableListOf()
        items.add(getOrderUI(name, quantity, price))

        orderProducts.value = items
    }

    fun postSale(client: String?,) {
        val items = products.value
        val orderID = orderID.value

        viewModelScope.launch(Dispatchers.IO) {

            useCase.postNewSale(orderID!!, client!!, items!!)
        }
    }

    fun getOrderID() {
        viewModelScope.launch(Dispatchers.IO) {
            currentOrderID.postValue(useCase.getOrderID())
        }
    }

    private fun getOrderUI(nome: String?, quantity: Int?, price: Double?): OrderItemUI {
        return OrderItemUI(nome!!, quantity!!, price!!)
    }
}