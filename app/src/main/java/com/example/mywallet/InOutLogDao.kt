package com.example.mywallet

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao


interface InOutLogDao{
    //suspend is a coroutine function
    @Insert
    suspend fun insertLog(inOutLog: InOutLog)

    @Query("SELECT * FROM in_out_log")
    fun getLogs(): LiveData<List<InOutLog>>

    @Query(value = "SElECT * FROM in_out_log WHERE id = :ID")
    suspend fun getALog(ID: Int) : InOutLog

    @Update
    suspend fun updateLog (inOutLog: InOutLog)

    @Delete
    suspend fun deleteLog (inOutLog: InOutLog)
}