package com.example.presentationlayer.viewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentationlayer.model.Message
import com.bumptech.glide.request.RequestOptions
import com.example.presentationlayer.model.Constants
import com.example.presentationlayer.R

class BotAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var conversation: MutableList<Message> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null

        when (viewType) {
            Constants.BOT -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.bot_message_layout, parent, false)
                return BotMessageViewHolder(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.user_message_layout, parent, false)
                return UserMessageViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = conversation.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType: Int = getItemViewType(position)
        if (itemViewType == Constants.USER) {

            val userMessageViewHolder = holder as UserMessageViewHolder
            userMessageViewHolder.userMessageTV.text = conversation[position].message ?: Constants.MESSAGE_TEXT_NULL
        }
        else if (itemViewType == Constants.BOT) {

            val botMessageViewHolder = holder as BotMessageViewHolder

            when {
                conversation[position].message != null -> {
                    botMessageViewHolder.botTextMessage.visibility = View.VISIBLE
                    botMessageViewHolder.botImageMessage.visibility = View.GONE

                    botMessageViewHolder.botTextMessage.text = conversation[position].message
                }
                conversation[position].imageUrl != null -> {
                    botMessageViewHolder.botTextMessage.visibility = View.GONE
                    botMessageViewHolder.botImageMessage.visibility = View.VISIBLE

                    val requestOptions: RequestOptions = RequestOptions().placeholder(R.drawable.loading_img)

                    Glide.with(holder.itemView.context)
                        .setDefaultRequestOptions(requestOptions)
                        .load(conversation[position].imageUrl)
                        .into(botMessageViewHolder.botImageMessage)
                }

                else -> {
                    botMessageViewHolder.botTextMessage.visibility = View.VISIBLE
                    botMessageViewHolder.botImageMessage.visibility = View.GONE

                    botMessageViewHolder.botTextMessage.text = Constants.MESSAGE_TEXT_NULL
                }
            }

        }

    }
    override fun getItemViewType(position: Int): Int {
        return conversation[position].sender
    }

    fun setConversation(conversation: MutableList<Message>) {
        this.conversation = conversation
        notifyDataSetChanged()
    }

}