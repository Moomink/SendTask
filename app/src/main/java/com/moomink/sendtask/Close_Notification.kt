package com.moomink.sendtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


class Close_Notification:AppCompatActivity() {
    val re_Intent:Intent = intent
    task.cancel() //TODO 10/18 ここから 関数呼び出し

}
