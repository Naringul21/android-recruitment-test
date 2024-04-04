package com.example.algoritmaapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.algoritmaapp.data.model.ResponseResult
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {
    @Insert
    suspend fun insert(result: ResponseResult)

    @Query("DELETE FROM responseresult")
    suspend fun deleteAll()

    @Query("SELECT * FROM responseresult")
    fun getAllLocalData(): Flow<List<ResponseResult>>
}