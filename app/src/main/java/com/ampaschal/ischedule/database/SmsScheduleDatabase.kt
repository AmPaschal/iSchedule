package com.ampaschal.ischedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ampaschal.ischedule.dao.SmsScheduleDao
import com.ampaschal.ischedule.entities.SmsSchedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(SmsSchedule::class), version = 1, exportSchema = false)
public abstract class SmsScheduleDatabase: RoomDatabase() {

    abstract fun smsScheduleDao(): SmsScheduleDao

    companion object{

        @Volatile
        private var INSTANCE: SmsScheduleDatabase? = null

        fun getDatabase(context: Context, scsope: CoroutineScope): SmsScheduleDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SmsScheduleDatabase::class.java,
                    "sms_schedule_database"
                ).addCallback(SmsScheduleDatabaseCallback(scsope)).build()

                INSTANCE = instance

                instance
            }
        }
    }

    class SmsScheduleDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch { populateDatabase(database.smsScheduleDao()) }
            }
        }

        private suspend fun populateDatabase(smsScheduleDao: SmsScheduleDao) {
            smsScheduleDao.deleteAll()

            val schedule1 = SmsSchedule("Happy Birthday Message", "HBD sweet heart",
                "+2347076473623", "4/4/21")
            smsScheduleDao.insertSchedule(schedule1)

            val schedule2 = SmsSchedule("Happy Marriage Message", "HML sweet heart",
                "+2347076473623", "4/4/21")
            smsScheduleDao.insertSchedule(schedule2)


        }
    }
}