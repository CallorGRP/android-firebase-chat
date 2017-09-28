package com.callor.firebaseexample

/**
 * SERVER_KEY를 본인의 fireBase Key로 변경한후
 * 이 클래스를 GoogleKey로 변경한 후 빌드하세요
 */
public class GoogleKey_Sample : Any() {
    companion object {
        val RC_SIGN_IN = 1001
        val FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send"
        val SERVER_KEY = "YOUR SERVER KEY"
    }
}