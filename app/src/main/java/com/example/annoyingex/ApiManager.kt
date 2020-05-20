package com.example.dotify

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.annoyingex.Messages
import com.google.gson.Gson

class ApiManager(context: Context) {
    val requestQueue = Volley.newRequestQueue(context);

    fun fetchMessages(onSuccess: (List<String>) -> Unit, onError: (String) -> Unit){
        val url = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val gson = Gson()
                val messageList: Messages = gson.fromJson(response, Messages::class.java)
                onSuccess(messageList.messages)
            },
            {
                onError.invoke(it.toString())
            })
        requestQueue.add(request)
    }
}