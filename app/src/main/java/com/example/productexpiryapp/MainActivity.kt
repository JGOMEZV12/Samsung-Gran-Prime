package com.example.productexpiryapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val addProductActivityRequestCode = 1
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ProductListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel.allProducts.observe(this) { products ->
            products?.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditProductActivity::class.java)
            startActivityForResult(intent, addProductActivityRequestCode)
        }

        setAlarm()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addProductActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val name = it.getStringExtra(AddEditProductActivity.EXTRA_NAME)
                val category = it.getStringExtra(AddEditProductActivity.EXTRA_CATEGORY)
                val quantity = it.getIntExtra(AddEditProductActivity.EXTRA_QUANTITY, 0)
                val expiryDateStr = it.getStringExtra(AddEditProductActivity.EXTRA_EXPIRY_DATE)
                val expiryDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(expiryDateStr)

                if (name != null && category != null && expiryDate != null) {
                    val product = Product(name = name, category = category, quantity = quantity, expiryDate = expiryDate)
                    productViewModel.insert(product)
                }
            }
        }
    }

    private fun setAlarm() {
        val alarmManager = getSystemService(ALARM_SERVICE) as android.app.AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = android.app.PendingIntent.getBroadcast(this, 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 8)

        alarmManager.setRepeating(
            android.app.AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            android.app.AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}
