package com.example.movieapp.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.movieapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val CHANNEL_ID = "Default"
const val NOTIFICATION_ID = 1

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d("MyDebug", "onMessageReceived $p0")
        super.onMessageReceived(p0)
        sendPush("Важно", "Мое сообщение!")

    }

    fun sendPush(title: String, text: String) {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_play)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_MAX)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    "Location",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
            notificationBuilder.setChannelId(CHANNEL_ID)
        }
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }
}
