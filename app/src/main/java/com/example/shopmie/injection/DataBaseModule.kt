package com.example.shopmie.injection

import android.app.Application
import androidx.room.Room
import com.example.shopmie.data.local.AppDataBase
import com.example.shopmie.data.local.OrderDAO
import com.example.shopmie.data.local.ProductDAO
import org.koin.dsl.module

fun provideDataBase(application: Application): AppDataBase {
    return Room.databaseBuilder(
        application,
        AppDataBase::class.java,
        "shopmie_database"
    ).fallbackToDestructiveMigration().build()
}

fun provideDao(orderDataBase: AppDataBase): OrderDAO = orderDataBase.getSalesDao()
fun provideProductDao(orderDataBase: AppDataBase): ProductDAO = orderDataBase.getProductsDao()

val dataBaseModule = module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
    single {provideProductDao(get())}
}