package com.example.algoritmaapp.data.network

import com.example.algoritmaapp.common.Constants
import com.example.algoritmaapp.common.Constants.URL
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import java.net.URISyntaxException


object SocketHandler {
    private lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket(URL)
        } catch (e: URISyntaxException) {

        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun connect() {
        mSocket.connect()
    }

    @Synchronized
    fun disconnect() {
        mSocket.disconnect()
    }

    @Synchronized
    fun onMessageReceived(listener: (String) -> Unit) {
        mSocket.on("message") { args ->
            val message = args[0] as String
            listener.invoke(message)
        }
    }
}
