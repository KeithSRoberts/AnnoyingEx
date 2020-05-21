package com.example.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.random.Random

class AnnoyingExWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    private val messages: List<String> = (context as AnnoyingExApp).messageList

    override fun doWork(): Result {
        var message = "Unable to retrieve message"
        if (messages.size != 0) {
            val index = Random.nextInt(0, messages.size - 1)
            message = messages[index]

        }
        (context as AnnoyingExApp).annoyingExNotificationManager.beginExNotifications(message)
        return Result.success()
    }
}