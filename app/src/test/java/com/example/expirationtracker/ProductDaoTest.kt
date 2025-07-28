package com.example.expirationtracker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.expirationtracker.data.AppDatabase
import com.example.expirationtracker.data.ProductDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import java.util.Date

@RunWith(RobolectricTestRunner::class)
class ProductDaoTest {
    private lateinit var productDao: ProductDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        productDao = db.productDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetProduct() = runBlocking {
        val product = Product(name = "Milk", category = "Dairy", quantity = 1, expirationDate = Date())
        productDao.insertProduct(product)
        val allProducts = productDao.getAllProducts().first()
        assertEquals(allProducts[0], product)
    }
}
