package com.callor.firebaseexample.firebase

data class UserData (
    var userEmailID: String? = null, // email 주소에서 @ 이전까지의 값.
    var fcmToken: String? = null
)
