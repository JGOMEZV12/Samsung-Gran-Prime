package com.example.productexpiryapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationHelper = NotificationHelper(context)
        notificationHelper.createNotificationChannel()

        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(context)
            val products = db.productDao().getAllProducts().value

            products?.forEach { product ->
                val calendar = Calendar.getInstance()
                calendar.time = product.expiryDate
                val expiryTime = calendar.timeInMillis
                val currentTime = System.currentTimeMillis()
                val diff = expiryTime - currentTime
                val days = (diff / (1000 * 60 * 60 * 24)).toInt()

                if (days == 5 || days == 2) {
                    notificationHelper.showNotification(product.name, days)
                }
            }
        }
    }
}
