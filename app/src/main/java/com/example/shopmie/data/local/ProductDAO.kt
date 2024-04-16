package com.example.shopmie.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shopmie.data.models.SalesProduct

@Dao
interface ProductDAO {

    @Insert
    fun insertProducts(vararg products: SalesProduct)

    @Query("SELECT * FROM order_products")
    fun getAllProducts(): List<SalesProduct>
}
