package com.miempresa.notificacionesv4

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private var notificationId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "channel_id"
            val channelName = "Channel Name"
            val channelDescription = "Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notificacionOreo(v: View?) {
        createNotificationChannel()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.notification_icon)
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationColor = getColor(R.color.notification_color)

        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.drawable.notification_icon)
            .setLargeIcon(largeIcon)
            .setContentTitle("Notification Tecsup")
            .setContentText("Programación en Móviles")
            .setStyle(NotificationCompat.BigTextStyle().bigText("Texto detallado de la notificación expandida..."))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setColor(notificationColor)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.ic_reply, "Reply", pendingIntent)
            .setProgress(0, 0, true)

        val expandedView = RemoteViews(packageName, R.layout.notification_expanded)
        expandedView.setTextViewText(R.id.textViewTitle, "Programación en móviles")
        expandedView.setTextViewText(R.id.textViewContent, "Esta es una notificación de Joshua")

        // Configurar el botón
        val closeButtonIntent = Intent(this, MainActivity::class.java)
        closeButtonIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        val closeButtonPendingIntent = PendingIntent.getActivity(this, 0, closeButtonIntent, 0)
        expandedView.setOnClickPendingIntent(R.id.button, closeButtonPendingIntent)

        notificationBuilder.setCustomBigContentView(expandedView)

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Handle the lack of permission
            return
        }
        notificationManager.notify(notificationId++, notificationBuilder.build())
    }
}
