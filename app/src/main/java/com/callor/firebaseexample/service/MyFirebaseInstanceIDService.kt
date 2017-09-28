package com.callor.firebaseexample.service

import android.util.Log

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "FirebaseInstanceId Refreshed token: " + refreshedToken!!)
    }

    companion object {
        private val TAG = "FCM_ID"
    }
}
