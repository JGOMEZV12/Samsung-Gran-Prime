package com.example.expirationtracker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.expirationtracker.ui.ProductListScreen
import org.junit.Rule
import org.junit.Test
import java.util.Date

class ProductListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productListScreen_displaysProducts() {
        val products = listOf(
            Product(1, "Milk", "Dairy", 1, Date()),
            Product(2, "Bread", "Bakery", 2, Date())
        )
        composeTestRule.setContent {
            ProductListScreen(products = products)
        }

        composeTestRule.onNodeWithText("Milk").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bread").assertIsDisplayed()
    }
}
