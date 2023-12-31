package com.example.presentationlayer.repo

import androidx.lifecycle.LiveData
import com.example.presentationlayer.api.rasa.ChatBotApiClient
import com.example.presentationlayer.model.BotMessage
import com.example.presentationlayer.model.Message
import com.example.presentationlayer.model.UserMessage
import com.example.presentationlayer.ui.login.userId
object ChatBotRepository {

    fun getBotMessages(): LiveData<List<BotMessage>> = ChatBotApiClient.getBotMessages()

    fun getConversation(): LiveData<MutableList<Message>> = ChatBotApiClient.getConversation()
    fun addConversation(message:Message) = ChatBotApiClient.addMessage(message)

    fun addUserMessageInConversation(userMessage: UserMessage) {
        ChatBotApiClient.addUserMessageInConversation(userMessage)
    }

    fun queryBot(message: String) {
        ChatBotApiClient.queryBot(userId, message)
    }
}