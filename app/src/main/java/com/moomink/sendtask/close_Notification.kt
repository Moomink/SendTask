package com.moomink.sendtask

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


@SuppressLint("Registered")
open class Close_Notification:AppCompatActivity() {
    val tmp = closeOne()
    private fun closeOne() {
        val reIntent: Intent = intent
        val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val data:Int = reIntent.getIntExtra("ID", 0)
        task.cancel(data)
    }
}
