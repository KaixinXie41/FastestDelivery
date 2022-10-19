package com.example.secondprojectbymvvm.view.supportChat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondprojectbymvvm.databinding.ActivitySupportChatBinding
import com.example.secondprojectbymvvm.model.Constants.OPEN_CART_PAGE
import com.example.secondprojectbymvvm.model.Constants.OPEN_ORDER_PAGE
import com.example.secondprojectbymvvm.model.Constants.OPEN_RATING_PAGE
import com.example.secondprojectbymvvm.model.Constants.RECEIVE_ID
import com.example.secondprojectbymvvm.model.Constants.SEND_ID
import com.example.secondprojectbymvvm.view.homepage.home.MainActivity
import com.example.secondprojectbymvvm.view.homepage.other.RatingActivity
import com.example.secondprojectbymvvm.view.supportChat.data.Message
import com.example.secondprojectbymvvm.view.supportChat.utils.BotResponse
import kotlinx.coroutines.*

class SupportChatActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySupportChatBinding
    private lateinit var adapter : MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        clickEvent()

    }

    private fun clickEvent() {
        binding.btnSend.setOnClickListener {
            sendMessage()
        }
        binding.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun initView() {
        adapter = MessageAdapter()
        binding.rvMessages.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvMessages.adapter = adapter

        val botList = listOf("Kai","Chris","Kay","Adam")
        val random = (0..3).random()
        customMessage("Hello! This is ${botList[random]}, how are you today? ")

    }

    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                adapter.insertMessage(Message(message, RECEIVE_ID))
                binding.rvMessages.scrollToPosition(adapter.itemCount -1)
            }
        }
    }
    private fun sendMessage(){
        val message = binding.etMessage.text.toString()
        if(message.isNotEmpty()){
            binding.etMessage.setText("")

            adapter.insertMessage(Message(message, SEND_ID))
            binding.rvMessages.scrollToPosition(adapter.itemCount -1)
            botResponse(message)
        }
    }

    private fun botResponse(message: String) {

        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main){
                val response = BotResponse.basicResponses(message)
                adapter.insertMessage(Message(response, RECEIVE_ID))
                binding.rvMessages.scrollToPosition(adapter.itemCount -1)
                when(response){
                    OPEN_ORDER_PAGE -> {
                        val intent = Intent(this@SupportChatActivity, MainActivity::class.java)
                        intent.putExtra(ORDER_PAGE, true)
                        startActivity(intent)
                    }

                    OPEN_CART_PAGE ->{
                        val intent = Intent(this@SupportChatActivity, MainActivity::class.java)
                        intent.putExtra(CART_PAGE, true)
                        startActivity(intent)
                    }
                    OPEN_RATING_PAGE ->{
                        val intent = Intent(this@SupportChatActivity, RatingActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                binding.rvMessages.scrollToPosition(adapter.itemCount -1)
            }
        }
    }

    companion object{
        const val ORDER_PAGE = "orderPage"
        const val CART_PAGE = "cartPage"
        const val Delivery_PAGE = "deliverPage"
    }
}