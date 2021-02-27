package com.ampaschal.ischedule.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sms_schedule")
data class SmsSchedule (
    val title: String,
    val message: String,
    val recipient: String,
    val date: String,

    @PrimaryKey(autoGenerate = true) val id: Long = 0
)