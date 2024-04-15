package com.example.shopmie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmie.data.repository.interfaces.ISalesRepository
import com.example.shopmie.domain.usecase.interfaces.ISalesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: ISalesRepository): ViewModel() {

    private val salesAmountUI = MutableLiveData<Double>()

    val salesAmount = salesAmountUI as LiveData<Double>

     fun getTotalSales() {
         viewModelScope.launch(Dispatchers.IO) {
             salesAmountUI.postValue(repo.getTotalSaleSum())
         }
    }

}