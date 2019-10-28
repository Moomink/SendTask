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
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, getString(R.string.channel_ID))
                .setContentTitle("TEST")
                .setContentText("testdayo")
                .setSmallIcon(R.drawable.icon_notification)
        startForeground(1, notification.build())

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
            val data: Int = reIntent.getIntExtra("Notification_ID", 0)
            task.cancel(data)
            Log.i("CHECK", "ID:$data")
        }
        stopSelf()
        Log.d("CHECK", "FINISHED")
        return START_NOT_STICKY
    }
}
