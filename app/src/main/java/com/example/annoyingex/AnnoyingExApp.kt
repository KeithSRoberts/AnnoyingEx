package com.example.annoyingex

import android.app.Application
import android.widget.Toast
import com.example.dotify.ApiManager

class AnnoyingExApp(): Application() {
    lateinit var apiManager: ApiManager
        private set

    var messageList = listOf<String>()
        private set

    override fun onCreate() {
        super.onCreate()
        apiManager = ApiManager(this)
        apiManager.fetchMessages({
            messageList = it;
        }, {
            Toast.makeText(applicationContext, "Could not fetch messages...", Toast.LENGTH_SHORT).show()
        })
    }
}