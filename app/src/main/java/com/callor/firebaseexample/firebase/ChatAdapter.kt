package com.callor.firebaseexample.firebase

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import com.callor.firebaseexample.R
import com.squareup.picasso.Picasso

import java.text.SimpleDateFormat
import java.util.Locale

class ChatAdapter(context: Context, resource: Int) : ArrayAdapter<ChatData>(context, resource) {
    private val mSimpleDateFormat = SimpleDateFormat("a h:mm", Locale.getDefault())
    private var mMyEmail: String? = null

    fun setEmail(email: String?) {
        mMyEmail = email
    }

    private fun setAnotherView(inflater: LayoutInflater): View {
        val convertView = inflater.inflate(R.layout.listitem_chat, null)
        val holder = ViewHolderAnother()
        holder.bindView(convertView)
        convertView.tag = holder
        return convertView
    }

    private fun setMySelfView(inflater: LayoutInflater): View {
        val convertView = inflater.inflate(R.layout.listitem_chat_my, null)
        val holder = ViewHolderMySelf()
        holder.bindView(convertView)
        convertView.tag = holder
        return convertView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewType = getItemViewType(position)
        val inflater = LayoutInflater.from(context)
        if (convertView == null) {
            if (viewType == TYPE_ANOTHER) {
                convertView = setAnotherView(inflater)
            } else {
                convertView = setMySelfView(inflater)
            }
        }

        if (convertView.tag is ViewHolderAnother) {
            if (viewType != TYPE_ANOTHER) {
                convertView = setAnotherView(inflater)
            }
            (convertView.tag as ViewHolderAnother).setData(position)
        } else {
            if (viewType != TYPE_MY_SELF) {
                convertView = setMySelfView(inflater)
            }
            (convertView.tag as ViewHolderMySelf).setData(position)
        }

        return convertView
    }


    override fun getViewTypeCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        val email = getItem(position)!!.userEmail
        return if (!TextUtils.isEmpty(mMyEmail) && mMyEmail == email) {
            TYPE_MY_SELF // 나의 채팅내용
        } else {
            TYPE_ANOTHER // 상대방의 채팅내용
        }
    }

    private inner class ViewHolderAnother {
        private var mImgProfile: ImageView? = null
        private var mTxtUserName: TextView? = null
        private var mTxtMessage: TextView? = null
        private var mTxtTime: TextView? = null

         fun bindView(convertView: View) {
            mImgProfile = convertView.findViewById(R.id.img_profile) as ImageView
            mTxtUserName = convertView.findViewById(R.id.txt_userName) as TextView
            mTxtMessage = convertView.findViewById(R.id.txt_message) as TextView
            mTxtTime = convertView.findViewById(R.id.txt_time) as TextView
        }

         fun setData(position: Int) {
            val chatData = getItem(position)
            Picasso.with(context).load(chatData!!.userPhotoUrl).into(mImgProfile)
            mTxtUserName!!.text = chatData.userName
            mTxtMessage!!.text = chatData.message
            mTxtTime!!.text = mSimpleDateFormat.format(chatData.time)
        }
    }

    private inner class ViewHolderMySelf {
        private var mImgProfile: ImageView? = null
        private var mTxtUserName: TextView? = null
        private var mTxtMessage: TextView? = null
        private var mTxtTime: TextView? = null

         fun bindView(convertView: View) {
            mImgProfile = convertView.findViewById(R.id.img_profile) as ImageView?
            mTxtUserName = convertView.findViewById(R.id.txt_userName) as TextView?
            mTxtMessage = convertView.findViewById(R.id.txt_message) as TextView?
            mTxtTime = convertView.findViewById(R.id.txt_time) as TextView?
        }

         fun setData(position: Int) {
            val chatData = getItem(position)
            Picasso.with(context).load(chatData!!.userPhotoUrl).into(mImgProfile)
            mTxtMessage!!.text = chatData.message
            mTxtTime!!.text = mSimpleDateFormat.format(chatData.time)
        }
    }

    companion object {
        private val TYPE_MY_SELF = 0
        private val TYPE_ANOTHER = 1
    }
}
