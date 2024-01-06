package com.example.ruanghukum.paging.aiChat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruanghukum.R
import com.example.ruanghukum.data.local.room.aiChat.AiChat

class ListAiChatAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var messages: MutableList<AiChat>
    private lateinit var context: Context

    init {
        messages = ArrayList()
        this.context = context
    }

    fun updateList(messages: List<AiChat>) {
        this.messages.clear()
        this.messages.addAll(messages)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            VIEW_TYPE_USER_MESSAGE_SENT  -> {
                SentMessageHolder(layoutInflater.inflate(R.layout.item_chat_sent, parent, false))
            }
            VIEW_TYPE_USER_MESSAGE_RECEIVED ->  {
                ReceivedMessageHolder(layoutInflater.inflate(R.layout.item_chat_received, parent, false))
            }
            else -> SentMessageHolder(layoutInflater.inflate(R.layout.item_chat_sent, parent, false)) //Generic return
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages.get(position)

        when (message) {
            is AiChat -> {
                if (message.flag.equals("SENT")) return VIEW_TYPE_USER_MESSAGE_SENT
                else return VIEW_TYPE_USER_MESSAGE_RECEIVED
            }
            //Handle other types of messages FILE/ADMIN ETC
            else ->  return -1
        }
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            VIEW_TYPE_USER_MESSAGE_SENT -> {
                holder as SentMessageHolder
                holder.bindView(context, messages.get(position))
            }
            VIEW_TYPE_USER_MESSAGE_RECEIVED -> {
                holder as ReceivedMessageHolder
                holder.bindView(context, messages.get(position))
            }
            //Handle other types of messages FILE/ADMIN ETC
        }
    }


    companion object {
        private val VIEW_TYPE_USER_MESSAGE_SENT = 10
        private val VIEW_TYPE_USER_MESSAGE_RECEIVED = 11
    }
}