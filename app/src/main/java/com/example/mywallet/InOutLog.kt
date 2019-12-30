package com.example.mywallet

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "in_out_log")


data class InOutLog(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Float,
    val type: Int, //1 = income and 0 = expenditure
    val created_at: Long = System.currentTimeMillis()
)
{

}