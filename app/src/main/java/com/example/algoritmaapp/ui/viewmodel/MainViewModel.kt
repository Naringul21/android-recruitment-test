package com.example.algoritmaapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algoritmaapp.common.DataConverter
import com.example.algoritmaapp.common.NetworkUtil
import com.example.algoritmaapp.data.model.ResponseResult
import com.example.algoritmaapp.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    private val _resultList = MutableStateFlow<List<ResponseResult>>(emptyList())
    val resultList: StateFlow<List<ResponseResult>> get() = _resultList

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> get() = _isConnected

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private fun loadDataFromUrl() {
        val socket = repository.loadDataFromUrl()


        socket.on("message") { args ->
            val array = Array<Any>(args.size) { 0 }
            viewModelScope.launch(Dispatchers.IO) {
                val list = DataConverter.convertToSocketModel(args, array)
                saveData(list)
                _isConnected.value = socket.connected()
                Log.d("list", "$list")
    }}}


    fun getData(context: Context) {
        val isConnected = NetworkUtil.checkInternetConnection(context)
        _isLoading.value = isConnected

        if (isConnected) {
            loadDataFromUrl()
            Log.d("connection", "$isConnected")
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getAllDataFromLocal().collect { data ->
                    _resultList.value = data
                    _isConnected.value = false
                    _isLoading.value = false
                    Log.d("localdata", "$data")
                }
            }
        }

    }

    private fun saveData(list: List<ResponseResult>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
            repository.saveData(list)
            _resultList.value = list
            _isLoading.value = false
        }
    }
}


//    fun convertData(message: String): List<ResponseResult> {
//        // JSON mesajını ResponseResult listesine dönüştürme işlemi
//        val gson = Gson()
//        val listType = object : TypeToken<List<ResponseResult>>() {}.type
//        return gson.fromJson(message, listType)
//    }}



