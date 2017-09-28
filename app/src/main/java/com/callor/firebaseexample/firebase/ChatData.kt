package com.callor.firebaseexample.firebase

data class ChatData (
    var firebaseKey: String? = null, // Firebase Realtime Database 에 등록된 Key 값
    var userName: String? = null, // 사용자 이름
    var userPhotoUrl: String? = null, // 사용자 사진 URL
    var userEmail: String? = null, // 사용자 이메일주소
    var message: String? = null, // 작성한 메시지
    var time: Long = 0 // 작성한 시간
)
