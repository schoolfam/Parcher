package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.network.ParcherApiService
import com.schoolfam.parcher.repository.AdminRepository
import com.schoolfam.parcher.repository.AttendanceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AttendanceViewModel(application: Application):AndroidViewModel(application) {

    private val attendanceRepository: AttendanceRepository

    val allAttendances: LiveData<List<Attendance>>

    init {
        val attendanceDao = ParcherDatabase.getInstance(application).attendanceDao()
        val parcherApiService = ParcherApiService.getInstance()
        attendanceRepository = AttendanceRepository(attendanceDao,parcherApiService,application)
        allAttendances = attendanceRepository.allAttendance()
    }

    fun findAttendanceById(id:Long): LiveData<Attendance> {
        return attendanceRepository.attendanceById(id)
    }

    fun findAttendanceByDateAndSection(date: Long,sectionId:Long): LiveData<List<Attendance>> {
        return attendanceRepository.allAttendanceByDateAndSection(date,sectionId)
    }
    fun findAttendanceByDateAndStudent(date: Long,studentId:Long): LiveData<Attendance> {
        return attendanceRepository.attendanceByDateAndStudent(date,studentId)
    }


    fun insertAttendance(attendance:Attendance) =  viewModelScope.launch (Dispatchers.IO){
        attendanceRepository.insertAttendance(attendance)
    }

    fun updateAdmin(attendance:Attendance) =  viewModelScope.launch (Dispatchers.IO){
        attendanceRepository.updateAttendance(attendance)
    }

    fun deleteAdmin(attendance:Attendance) =  viewModelScope.launch (Dispatchers.IO){
        attendanceRepository.deleteAttendance(attendance)
    }
}