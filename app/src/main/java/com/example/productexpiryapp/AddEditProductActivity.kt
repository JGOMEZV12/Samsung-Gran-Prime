package com.example.productexpiryapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class AddEditProductActivity : AppCompatActivity() {

    private lateinit var editProductNameView: EditText
    private lateinit var editProductCategoryView: EditText
    private lateinit var editProductQuantityView: EditText
    private lateinit var editProductExpiryDateView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_product)
        editProductNameView = findViewById(R.id.editTextProductName)
        editProductCategoryView = findViewById(R.id.editTextProductCategory)
        editProductQuantityView = findViewById(R.id.editTextProductQuantity)
        editProductExpiryDateView = findViewById(R.id.editTextProductExpiryDate)

        val button = findViewById<Button>(R.id.buttonSave)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editProductNameView.text) ||
                TextUtils.isEmpty(editProductCategoryView.text) ||
                TextUtils.isEmpty(editProductQuantityView.text) ||
                TextUtils.isEmpty(editProductExpiryDateView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editProductNameView.text.toString()
                val category = editProductCategoryView.text.toString()
                val quantity = editProductQuantityView.text.toString().toInt()
                val expiryDate = editProductExpiryDateView.text.toString()
                replyIntent.putExtra(EXTRA_NAME, name)
                replyIntent.putExtra(EXTRA_CATEGORY, category)
                replyIntent.putExtra(EXTRA_QUANTITY, quantity)
                replyIntent.putExtra(EXTRA_EXPIRY_DATE, expiryDate)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_NAME = "com.example.android.productlistsql.NAME"
        const val EXTRA_CATEGORY = "com.example.android.productlistsql.CATEGORY"
        const val EXTRA_QUANTITY = "com.example.android.productlistsql.QUANTITY"
        const val EXTRA_EXPIRY_DATE = "com.example.android.productlistsql.EXPIRY_DATE"
    }
}
