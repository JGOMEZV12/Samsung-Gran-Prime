package com.example.productexpiryapp

import androidx.lifecycle.LiveData

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()

    suspend fun insert(product: Product) {
        productDao.insert(product)
    }

    suspend fun delete(product: Product) {
        productDao.delete(product)
    }
}
