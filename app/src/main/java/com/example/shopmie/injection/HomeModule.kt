package com.example.shopmie.injection

import com.example.shopmie.data.repository.SalesRepository
import com.example.shopmie.data.repository.interfaces.ISalesRepository
import com.example.shopmie.domain.usecase.SalesUseCase
import com.example.shopmie.domain.usecase.interfaces.ISalesUseCase
import com.example.shopmie.ui.home.HomeViewModel
import com.example.shopmie.ui.sales.SalesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<ISalesRepository> {
        SalesRepository(get(), get())
    }
    viewModel {
        HomeViewModel(get())
    }

}

val salesModule = module {
        single<ISalesUseCase> {
            SalesUseCase(get())
        }
        viewModel {
            SalesViewModel(get())
        }
}

