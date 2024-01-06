package com.example.ruanghukum.paging.aiChat

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.room.aiChat.AiChat

class ReceivedMessageHolder(view: View) : RecyclerView.ViewHolder(view) {

    val messageText = view.findViewById<TextView>(R.id.tv_chat_message_received)
    val timeText = view.findViewById<TextView>(R.id.tv_chat_timestamp_received)
    val dateText = view.findViewById<TextView>(R.id.tv_chat_date_received)

    fun bindView(context: Context, message: AiChat) {
        messageText.text = message.message

        timeText.text = message.createdAt

        dateText.visibility = View.GONE
        dateText.text = message.createdAt
    }
}