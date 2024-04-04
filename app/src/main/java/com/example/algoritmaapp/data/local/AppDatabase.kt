package com.example.algoritmaapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.algoritmaapp.data.model.ResponseResult

@Database(entities = [ResponseResult::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao
}