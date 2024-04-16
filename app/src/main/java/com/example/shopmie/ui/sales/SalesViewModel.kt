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

    val isOrderDataFilled: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    var client: String? = null
        set(value) {
            updateIsOrderFilled()
            field = value
        }

    fun addProduct(name: String?, quantity: Int?, price: Double?) {
        val items = orderProducts.value?.toMutableList() ?: mutableListOf()

        if (name == null || quantity == null || price == null) return
        
        items.add(OrderItemUI(name, quantity, price))
        orderProducts.value = items
        updateIsOrderFilled()
    }

    private fun updateIsOrderFilled(){
        val isClientValid = !client.isNullOrBlank()
        val isProductListValid = orderProducts.value.isNullOrEmpty()

        isOrderDataFilled.value = isClientValid && isProductListValid
    }

    fun postSale() {
        val items = products.value
        val orderID = orderID.value

        if (client == null || items == null || orderID == null) return

        viewModelScope.launch(Dispatchers.IO) {
            useCase.postNewSale(orderID, client.toString(), items)
        }
    }

    fun getOrderID() {
        viewModelScope.launch(Dispatchers.IO) {
            currentOrderID.postValue(useCase.getOrderID())
        }
    }

}