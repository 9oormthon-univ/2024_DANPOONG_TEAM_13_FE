package com.daon.onjung

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.daon.onjung.network.model.response.TicketFcmResponse
import com.google.firebase.messaging.Constants.MessageNotificationKeys
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import java.net.URLEncoder

class OnjungFCMService : FirebaseMessagingService() {

    private val TAG = "fcm"
    private val CHANNEL_ID = "notification_remote_channel"

    private lateinit var notificationManager: NotificationManager

    private val gson = Gson()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    // Token 생성
    override fun onNewToken(token: String) {
        Log.d(TAG, "new Token: $token")
    }

    // foreground 메세지 수신시 동작 설정
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.from)

        // 받은 remoteMessage의 값 출력해보기. 데이터메세지 / 알림메세지
        val messageData = "${remoteMessage.notification?.let {
            "${it.title} ${it.body}"
        }}"
        Log.d(TAG, "Message data : $messageData")
        Log.d(TAG, "Message noti : ${remoteMessage.notification}")

        sendNotification(remoteMessage, remoteMessage.data)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel =
                NotificationChannel(CHANNEL_ID, "notification_channel", importance)

            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // 메세지 알림 생성
    private fun sendNotification(remoteMessage: RemoteMessage, ticketData: Map<String, String>) {
        Log.d(TAG, "sendNotification: ${remoteMessage.notification?.title}")
        Log.d(TAG, "sendNotification: ${remoteMessage.notification?.body}")
        Log.d(TAG, "sendNotification: ${ticketData.toList()}")
        val pendingIntent = getFcmPendingIntent(this, ticketData)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.mipmap.ic_launcher) // 아이콘 설정
            .setContentTitle(
                remoteMessage.notification?.title.orEmpty()
                    .ifEmpty { ticketData["title"] ?: "\uD83C\uDF89 1만원 금액 식권 당첨" } // 제목 설정
            )
            .setContentText(
                remoteMessage.notification?.body.orEmpty()
                    .ifEmpty { ticketData["body"] ?: "지금 바로 클릭해서 따뜻한 한 끼를 확인해 보세요!" } // 메시지 내용 설정
            )
            .setAutoCancel(true) // 알람 클릭 시 삭제
            .setDefaults(Notification.DEFAULT_ALL) // 진동, 소리, 불빛 설정
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent


        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }

    private fun getFcmPendingIntent(
        context: Context,
        ticketData: Map<String, String>
    ): PendingIntent {
        Log.d(TAG, "Received Ticket Data: $ticketData")

        val jsonString = gson.toJson(ticketData)
        val ticket = gson.fromJson(jsonString, TicketFcmResponse::class.java)

        Log.d(TAG, "Received Ticket: $ticket")

        // URI 쿼리 파라미터를 안전하게 인코딩
        val encodedStoreName = URLEncoder.encode(ticket.store_name, "UTF-8")
        val encodedAddress = URLEncoder.encode(ticket.address, "UTF-8")
        val encodedCategory =
            URLEncoder.encode(ticket.category.toString(), "UTF-8") // Enum to String
        val encodedUserName = URLEncoder.encode(ticket.user_name, "UTF-8")

        val encodedExpirationDate = URLEncoder.encode(ticket.expiration_date, "UTF-8")
        val encodedLogoImgUrl = URLEncoder.encode(ticket.logo_img_url, "UTF-8")

        // Deeplink 생성
        val deeplink = "app://daon.onjung?storeName=$encodedStoreName" +
                "&address=$encodedAddress" +
                "&category=$encodedCategory" +
                "&userName=$encodedUserName" +
                "&expirationDate=$encodedExpirationDate" +
                "&logoImgUrl=$encodedLogoImgUrl"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(deeplink)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // PendingIntent 생성
        return PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }


    private fun keyWithOldPrefix(key: String): String {
        if (!key.startsWith(MessageNotificationKeys.NOTIFICATION_PREFIX)) {
            return key
        }

        return key.replaceFirst(
            MessageNotificationKeys.NOTIFICATION_PREFIX,
            MessageNotificationKeys.NOTIFICATION_PREFIX_OLD
        )
    }

    override fun handleIntent(intent: Intent?) {
        val new = intent?.apply {
            val temp = extras?.apply {
                remove(MessageNotificationKeys.ENABLE_NOTIFICATION)
                remove(keyWithOldPrefix(MessageNotificationKeys.ENABLE_NOTIFICATION))
            }
            replaceExtras(temp)
        }
        super.handleIntent(new)
    }

}