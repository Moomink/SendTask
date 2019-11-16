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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlin.random.Random
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    var cnt = Random.nextInt()


    /*getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)*/
    fun notification(view: View) {

        val words: String = TEXT.text.toString()
        val title: String = Title.text.toString()
        val subtitle: String = SubTitle.text.toString()
        if (words == "" || title == "") {
            Toast.makeText(this, "文字を入力してください", Toast.LENGTH_LONG).show()
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val id: String = getString(R.string.channel_ID)
            val descriptionText = getString(R.string.description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance)  //TODO チャンネル整理
                .apply { description = descriptionText }

            val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            task.createNotificationChannel(channel)
            val intent: Intent = Intent(
                this.applicationContext,
                Close_Notification::class.java
            ) //Intent 何でも入るので色々入れてる
                .putExtra("Notification_ID", cnt)
            val result: PendingIntent =
                PendingIntent.getForegroundService(this, cnt, intent, 0) //FIXME なんとかしろ

            val base: NotificationCompat.Builder =
                NotificationCompat.Builder(applicationContext, id)
                    .setContentTitle(title)
                    .setContentText(words)
                    .setContentInfo(subtitle)
                    .setSubText(subtitle)
                    .setSmallIcon(R.drawable.icon_notification)
                    .setOngoing(true)
                    .addAction(R.drawable.icon_notification, "完了", result)


            task.notify(cnt, base.build())   //fixme cntをどうにかする
        } else {
            // val task = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val base: NotificationCompat.Builder = NotificationCompat.Builder(this)
                .setContentText(words)
                .setContentTitle(title)
                .setSubText(subtitle)
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
        cnt = Random.nextInt()
    }

}

