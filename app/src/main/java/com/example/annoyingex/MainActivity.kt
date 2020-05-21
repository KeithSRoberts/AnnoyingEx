package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import com.example.annoyingex.AnnoyingExNotificationManager.Companion.MESSAGE_KEY
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val workerManager = (application as AnnoyingExApp).annoyingExWorkerManager
        // Display the current message in our text view if available
        intent.getStringExtra(MESSAGE_KEY)?.let {
            textMessage.text = it
        }
        // Assign start functionality to start button
        btnStart.setOnClickListener {
            workerManager.startMessages()
        }
        // Assign stop functionality to stop button
        btnStop.setOnClickListener {
            workerManager.stopMessages()
        }

    }
}
