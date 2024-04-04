package com.example.algoritmaapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResponseResult(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val image: String,
    val name: String,
    val vOne: String,
    val vTwo: String,
    val vThree: String,
    val vFour: String,
    val rate: String,
    val date: String
)
