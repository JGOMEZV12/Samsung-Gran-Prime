package com.example.expirationtracker

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.expirationtracker.worker.ExpirationWorker
import java.util.concurrent.TimeUnit

class ExpirationTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
        val workRequest = PeriodicWorkRequestBuilder<ExpirationWorker>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }
}
