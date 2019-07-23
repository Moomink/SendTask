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
     fun notification(view: View){
        val words:String = editText.text.toString()
        val subTitle:String = SubTitle.text.toString()
        val title:String = Title.text.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val id = getString(R.string.channel_ID)
            val descriptionText = getString(R.string.description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, title,importance)
                                                .apply{description = descriptionText}

//            var intent:Intent = Intent(this,Close_Notification)
//            val Result:PendingIntent = PendingIntent(this,cnt,)


            val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            task.createNotificationChannel(channel)

            val base:NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,id)
                .setContentText(words)
                .setContentInfo(title)
                .setContentTitle(subTitle)
                .setSmallIcon(R.drawable.icon_notification)
                .setOngoing(true)
                .setTicker(getString(R.string.Sent))
//                .addAction(R.drawable.icon_notification,"完了",)


            task.notify(cnt,base.build())
        }else{
            val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val base:NotificationCompat.Builder = NotificationCompat.Builder(applicationContext)
                .setContentText(subTitle)
                .setContentTitle(title)
                .setContentInfo(title)
                .setTicker(getString(R.string.Sent))
                .setSmallIcon(R.drawable.icon_notification)

//            var intent = Intent(this,Close_Notification.class)


            task.notify(cnt,base.build())
        }


        Log.d("Debug",cnt.toString())
        cnt++
    }
     fun close_Notification(view:View){
         val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        task.cancelAll()
    }

}

