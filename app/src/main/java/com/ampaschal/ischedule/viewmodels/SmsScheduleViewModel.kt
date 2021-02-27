package com.ampaschal.ischedule.viewmodels

import androidx.lifecycle.*
import com.ampaschal.ischedule.entities.SmsSchedule
import com.ampaschal.ischedule.repositories.SmsScheduleRepository
import kotlinx.coroutines.launch

class SmsScheduleViewModel (private val repository: SmsScheduleRepository): ViewModel() {

    val schedules: LiveData<List<SmsSchedule>> = repository.schedules.asLiveData()

    fun insertSchedule(smsSchedule: SmsSchedule) = viewModelScope.launch {
        repository.insertSchedule(smsSchedule)
    }

}

class SmsScheduleViewModelFactory(private val repository: SmsScheduleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SmsScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SmsScheduleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}