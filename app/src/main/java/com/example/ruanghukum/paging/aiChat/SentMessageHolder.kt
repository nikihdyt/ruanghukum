package com.example.ruanghukum.paging.aiChat

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.room.aiChat.AiChat

class SentMessageHolder(view: View) : RecyclerView.ViewHolder(view) {

    val messageText = view.findViewById<TextView>(R.id.tv_chat_message_sent)
    val timeText = view.findViewById<TextView>(R.id.tv_chat_timestamp_sent)
    val dateText = view.findViewById<TextView>(R.id.tv_chat_date_sent)

    fun bindView(context: Context, message: AiChat) {
        messageText.text = message.message

        timeText.text = message.createdAt

        dateText.visibility = View.GONE
        dateText.text = message.createdAt
    }
}