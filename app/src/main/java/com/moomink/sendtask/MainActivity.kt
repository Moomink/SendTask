package com.moomink.sendtask

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var cnt = 0


    /*getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)*/
    fun noti(view: View){
        val words:String = editText.text.toString()
        val title:String = Title.text.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val id = getString(R.string.channel_ID)
            val descriptionText = getString(R.string.description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id,name,importance)
                                                .apply{description = descriptionText}
            val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            task.createNotificationChannel(channel)

            val base:NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,id)
                .setContentText(words)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.icon_notification)

            task.notify(cnt,base.build())
        }else{
            val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val base:NotificationCompat.Builder = NotificationCompat.Builder(applicationContext)
                .setContentText(words)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.icon_notification)

            task.notify(cnt,base.build())
        }


        Log.d("Debug",cnt.toString())
        cnt++
    }

}

