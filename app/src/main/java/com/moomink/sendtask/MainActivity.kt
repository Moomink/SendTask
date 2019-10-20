package com.moomink.sendtask

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    var cnt = 0


    /*getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)*/
    fun notification(view: View) {

        val words: String = TEXT.text.toString()
        val title: String = Title.text.toString()
        val test: String = SubTitle.text.toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val id = getString(R.string.channel_ID)
            val descriptionText = getString(R.string.description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance)  //TODO チャンネル整理
                .apply { description = descriptionText }

            val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            task.createNotificationChannel(channel)
            val intent: Intent = Intent(this, Close_Notification::class.java)
                .putExtra("ID", cnt)
            val result: PendingIntent =
                PendingIntent.getActivity(this, cnt, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val base: NotificationCompat.Builder =
                NotificationCompat.Builder(applicationContext, id)
                    .setContentTitle(title)
                    .setContentText(words)
                    .setContentInfo(test)
                    .setSmallIcon(R.drawable.icon_notification)
                    .setOngoing(true)
                    .addAction(R.drawable.icon_notification, "完了", result) //FIXME ここらへんからエラーをはいてるかも


            task.notify(cnt, base.build())   //fixme cntをどうにかする
        } else {
            // val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val base: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext)
                .setContentText(words)
                .setContentTitle(title)
                .setSubText(test)
                .setSmallIcon(R.drawable.icon_notification)

//            var intent = Intent(this,Close_Notification.class)
            val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            task.notify(cnt, base.build())
        }

        Log.d("ID", cnt.toString())
        cnt++
    }

    fun closeAll(view: View) {
        val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        task.cancelAll()
    }

}

