package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData

import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.attendance.AttendanceDao
import java.util.*

class AttendanceRepository(private val attendanceDao: AttendanceDao) {

    fun allAttendance(): LiveData<List<Attendance>> =  attendanceDao.getAllAttendances()

    fun allAttendanceByDateAndSection(date:Long,sectionId:Long): LiveData<List<Attendance>> =  attendanceDao.getAttendanceByDateAndSection(date,sectionId)

    fun attendanceByDateAndStudent(date:Long,studentId:Long): LiveData<Attendance> =  attendanceDao.getAttendanceByDateAndStudent(date,studentId)

    fun attendanceById(id:Long): LiveData<Attendance> = attendanceDao.getAttendanceById(id)

    fun insertAttendance(attendance: Attendance){
        attendanceDao.insertAttendance(attendance)
    }
    fun updateAttendance(attendance: Attendance){
        attendanceDao.updateAttendance(attendance)
    }
    fun deleteAttendance(attendance: Attendance){
        attendanceDao.deleteAttendance(attendance)
    }
}