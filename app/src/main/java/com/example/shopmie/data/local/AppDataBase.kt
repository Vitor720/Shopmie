package com.example.shopmie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopmie.data.models.OrderData
import com.example.shopmie.data.models.SalesProduct

@Database(entities = [OrderData::class, SalesProduct::class], version = 1)
abstract class AppDataBase(): RoomDatabase(){

    abstract fun getSalesDao(): OrderDAO
}