package com.callor.firebaseexample.service

import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log

import com.callor.firebaseexample.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage!!.notification != null) {
            val body = remoteMessage.notification.body
            Log.d(TAG, "Notification Body: " + body)

            val notificationBuilder = NotificationCompat.Builder(applicationContext)
                    .setSmallIcon(R.mipmap.ic_launcher) // 알림 영역에 노출 될 아이콘.
                    .setContentTitle(getString(R.string.app_name)) // 알림 영역에 노출 될 타이틀
                    .setContentText(body) // Firebase Console 에서 사용자가 전달한 메시지내용)

            val notificationManagerCompat = NotificationManagerCompat.from(applicationContext)
            notificationManagerCompat.notify(0x1001, notificationBuilder.build())
        }
    }

    companion object {
        private val TAG = "FCM_MESSAGE"
    }
}
