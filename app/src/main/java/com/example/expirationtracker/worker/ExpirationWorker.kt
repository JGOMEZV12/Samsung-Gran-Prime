package com.example.expirationtracker.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expirationtracker.data.AppDatabase
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

class ExpirationWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val database = AppDatabase.getDatabase(applicationContext)
        val productDao = database.productDao()

        productDao.getAllProducts().collect { products ->
            val today = Calendar.getInstance()
            val twoDaysFromNow = Calendar.getInstance()
            twoDaysFromNow.add(Calendar.DAY_OF_YEAR, 2)
            val fiveDaysFromNow = Calendar.getInstance()
            fiveDaysFromNow.add(Calendar.DAY_OF_YEAR, 5)

            for (product in products) {
                val expirationDate = Calendar.getInstance()
                expirationDate.time = product.expirationDate

                if (expirationDate.after(today) && expirationDate.before(twoDaysFromNow)) {
                    sendNotification(product.name, "Expires in 2 days")
                } else if (expirationDate.after(today) && expirationDate.before(fiveDaysFromNow)) {
                    sendNotification(product.name, "Expires in 5 days")
                }
            }
        }

        return Result.success()
    }

    private fun sendNotification(title: String, message: String) {
        NotificationHelper.sendNotification(applicationContext, title, message)
    }
}
