package com.example.expirationtracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expirationtracker.Product
import java.util.Date

@Composable
fun ProductListScreen(products: List<Product>) {
    LazyColumn {
        items(products) { product ->
            ProductListItem(product = product)
        }
    }
}

@Composable
fun ProductListItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.h6)
            Text(text = "Category: ${product.category}", style = MaterialTheme.typography.body1)
            Text(text = "Quantity: ${product.quantity}", style = MaterialTheme.typography.body1)
            Text(text = "Expires on: ${product.expirationDate}", style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun PreviewProductListScreen() {
    val products = listOf(
        Product(1, "Milk", "Dairy", 1, Date()),
        Product(2, "Bread", "Bakery", 2, Date()),
        Product(3, "Cheese", "Dairy", 1, Date())
    )
    ProductListScreen(products = products)
}
