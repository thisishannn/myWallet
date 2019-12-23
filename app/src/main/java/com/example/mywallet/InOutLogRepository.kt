package com.example.mywallet

import androidx.lifecycle.LiveData

class InOutLogReppository(private val inOutLogDao: InOutLogDao) {
    //Room to execute all queries in a separate thread

    val  allLogs: LiveData<List<InOutLog>> =  inOutLogDao.getLogs()

    suspend fun insertLog(inOutLog: InOutLog) {
        inOutLogDao.insertLog(inOutLog)

    }

}