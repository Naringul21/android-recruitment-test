package com.example.algoritmaapp.data.repository

import com.example.algoritmaapp.data.local.DataDao
import com.example.algoritmaapp.data.model.ResponseResult
import com.example.algoritmaapp.data.network.SocketHandler
//import io.socket.client.IO
//import io.socket.client.Socket
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepository @Inject constructor(private val dataDao: DataDao) {

    fun loadDataFromUrl(): Socket {
        SocketHandler.setSocket()
        SocketHandler.connect()
        val mSocket = SocketHandler.getSocket()
        mSocket.connect()
        val options = IO.Options()
        options.reconnection = true
        options.forceNew = true
        return mSocket
    }

    suspend fun saveData(result: List<ResponseResult>) {
        withContext(Dispatchers.IO) {
            result.forEach { resultResponse ->
                dataDao.insert(resultResponse)
            }
        }
    }

    suspend fun deleteAllData() {
        withContext(Dispatchers.IO) {
            dataDao.deleteAll()
        }
    }

    fun getAllDataFromLocal(): Flow<List<ResponseResult>> {
        return dataDao.getAllLocalData()
    }

}