package com.example.shopmie.injection

import com.example.shopmie.data.repository.SalesRepository
import com.example.shopmie.data.repository.interfaces.ISalesRepository
import com.example.shopmie.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<ISalesRepository> {
        SalesRepository(get())
    }
    viewModel {
        HomeViewModel(get())
    }

}


