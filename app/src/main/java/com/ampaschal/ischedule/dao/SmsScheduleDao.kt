package com.ampaschal.ischedule.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ampaschal.ischedule.entities.SmsSchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface SmsScheduleDao {

    @Query("SELECT * FROM sms_schedule ORDER BY date ASC")
    fun getSmsSchedules(): Flow<List<SmsSchedule>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSchedule(smsSchedule: SmsSchedule)

    @Query("DELETE FROM sms_schedule")
    suspend fun deleteAll()
}