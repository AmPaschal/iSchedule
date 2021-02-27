package com.ampaschal.ischedule

import android.app.Application
import com.ampaschal.ischedule.database.SmsScheduleDatabase
import com.ampaschal.ischedule.repositories.SmsScheduleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SmsScheduleApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { SmsScheduleDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { SmsScheduleRepository(database.smsScheduleDao()) }
}