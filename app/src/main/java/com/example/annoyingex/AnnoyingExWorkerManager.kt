package com.example.annoyingex

import android.content.Context
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class AnnoyingExWorkerManager(private val context: Context) {
    companion object {
        const val REQUEST_TAG = "REQUEST_TAG"
    }

    private var workManager = WorkManager.getInstance(context)

    private fun isActive(): Boolean {
        return when (workManager.getWorkInfosByTag(REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun startMessages() {
        if (!isActive()) {
            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<AnnoyingExWorker>(20, TimeUnit.MINUTES)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .addTag(REQUEST_TAG)
                .build()

            workManager.enqueue(workRequest)
        }
    }

    fun stopMessages() {
        workManager.cancelAllWorkByTag(REQUEST_TAG)
    }
}