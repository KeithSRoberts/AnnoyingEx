package com.example.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AnnoyingExWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    override fun doWork(): Result {
        (context as AnnoyingExApp).annoyingExNotificationManager.beginExNotifications()
        return Result.success()
    }
}