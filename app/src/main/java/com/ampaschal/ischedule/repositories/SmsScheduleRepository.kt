package com.ampaschal.ischedule.repositories

import androidx.annotation.WorkerThread
import com.ampaschal.ischedule.dao.SmsScheduleDao
import com.ampaschal.ischedule.entities.SmsSchedule
import kotlinx.coroutines.flow.Flow

class SmsScheduleRepository(private val smsScheduleDao: SmsScheduleDao) {

    val schedules: Flow<List<SmsSchedule>> = smsScheduleDao.getSmsSchedules()

    @WorkerThread
    suspend fun insertSchedule(smsSchedule: SmsSchedule) {
        smsScheduleDao.insertSchedule(smsSchedule)
    }
}