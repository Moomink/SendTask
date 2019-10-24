package com.moomink.sendtask

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat


class Close_Notification : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d("CHECK", "OK_Create")

    }

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val reIntent: Intent? = intent
        if (reIntent == null) {
            Log.e("CHECK", "NG")

        } else {
            Log.d("CHECK", "OK")
            val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val data: Int = reIntent.getIntExtra("ID", 0)
            task.cancel(data)
        }
        stopSelf()
        Log.d("CHECK","FINISHED")
        return START_NOT_STICKY
    }
}
