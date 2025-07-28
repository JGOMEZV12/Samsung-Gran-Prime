package com.example.expirationtracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.expirationtracker.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>

    @Insert
    suspend fun insertProduct(product: Product)
}
