package com.example.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import com.example.busschedule.database.ScheduleDao
import com.example.busschedule.database.schedule
import kotlinx.coroutines.flow.Flow


class BusScheduleViewModel(private val scheduleDao:ScheduleDao): ViewModel()
{
    fun fullSchedule(): Flow<List<schedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): Flow<List<schedule>> = scheduleDao.getByStopName(name)
}