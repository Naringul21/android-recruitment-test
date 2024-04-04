package com.example.algoritmaapp.common

import com.example.algoritmaapp.data.model.ResponseResult
import org.json.JSONObject

object DataConverter {

//    fun convertToSocketModels(jsonString: String): List<ResponseResult> {
//        val socketList = mutableListOf<ResponseResult>()
//
//        try {
//            val jsonObject = JSONObject(jsonString)
//            val jsonArray = jsonObject.getJSONArray("result")
//
//            for (i in 0 until jsonArray.length()) {
//                val innerObject = jsonArray.getJSONObject(i)
//
//                val upDown = innerObject.optString("0")
//                val name = innerObject.optString("1")
//                val valueOne = innerObject.optString("2")
//                val valueTwo = innerObject.optString("3")
//                val valueThree = innerObject.optString("4")
//                val valueFour = innerObject.optString("5")
//                val rating = innerObject.optString("6")
//                val date = innerObject.optString("7")
//
//                val responseResult = ResponseResult(
//                    image = upDown,
//                    name = name,
//                    vOne = valueOne,
//                    vTwo = valueTwo,
//                    vThree = valueThree,
//                    vFour = valueFour,
//                    rate = rating,
//                    date = date
//                )
//                socketList.add(responseResult)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        return socketList
//    }

   fun convertToSocketModel (from: Array<out Any>, to: Array<Any>): List<ResponseResult> {
        val socketList = mutableListOf<ResponseResult>()
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
        val list = mutableListOf<String>()
        for (i in to.indices) {
            list.add(to[i].toString())
        }
        val jsonObject = JSONObject(list[0])
        val jsonArray = jsonObject.getJSONArray("result")
        for (i in 0 until jsonArray.length())
        {
            val jsonObject = jsonArray.getJSONObject(i)

            val upDown = jsonObject.optString("0")
            val name = jsonObject.optString("1")
            val valueOne = jsonObject.optString("2")
            val valueTwo = jsonObject.optString("3")
            val valueThree = jsonObject.optString("4")
            val valueFour = jsonObject.optString("5")
            val rating = jsonObject.optString("6")
            val date = jsonObject.optString("7")

            val responseResult = ResponseResult(
                image = upDown,
                name = name,
                vOne = valueOne,
                vTwo = valueTwo,
                vThree = valueThree,
                vFour = valueFour,
                rate = rating,
                date = date
            )
            socketList.add(responseResult)
        }
        return socketList
    }
}