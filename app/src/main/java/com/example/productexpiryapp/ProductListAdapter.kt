package com.example.productexpiryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ProductListAdapter : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameItemView: TextView = itemView.findViewById(R.id.textViewProductName)
        private val productCategoryItemView: TextView = itemView.findViewById(R.id.textViewProductCategory)
        private val productQuantityItemView: TextView = itemView.findViewById(R.id.textViewProductQuantity)
        private val productExpiryDateItemView: TextView = itemView.findViewById(R.id.textViewProductExpiryDate)

        fun bind(product: Product) {
            productNameItemView.text = product.name
            productCategoryItemView.text = product.category
            productQuantityItemView.text = product.quantity.toString()
            productExpiryDateItemView.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(product.expiryDate)
        }

        companion object {
            fun create(parent: ViewGroup): ProductViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ProductViewHolder(view)
            }
        }
    }

    class ProductsComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
