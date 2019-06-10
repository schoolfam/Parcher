package com.schoolfam.parcher.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.attendance.AttendanceDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.*

class AttendanceRepository(private val attendanceDao: AttendanceDao, private val attendanceService: ParcherApiService?) {

    private fun cache(attendance: Attendance){
        attendanceDao.insertAttendance(attendance)
    }

    fun insertAttendance(attendance: Attendance){
        if (insertAttendanceToAPI(attendance)) {
            attendanceDao.insertAttendance(attendance)
        }
    }
    fun updateAttendance(attendance: Attendance){
        if (updateAttendanceInAPI(attendance)) {
            attendanceDao.updateAttendance(attendance)
        }
    }
    fun deleteAttendance(attendance: Attendance){
        if (deleteAttendanceFromAPI(attendance)) {
            attendanceDao.deleteAttendance(attendance)
        }
    }

    fun allAttendance(): LiveData<List<Attendance>> =  attendanceDao.getAllAttendances()

    fun allAttendanceByDateAndSection(date:Long,sectionId:Long): LiveData<List<Attendance>> =  attendanceDao.getAttendanceByDateAndSection(date,sectionId)

    fun attendanceByDateAndStudent(date:Long,studentId:Long): LiveData<Attendance> =  attendanceDao.getAttendanceByDateAndStudent(date,studentId)

    fun attendanceById(id:Long): LiveData<Attendance> = attendanceDao.getAttendanceById(id)

    //network functions
    @WorkerThread
    fun insertAttendanceToAPI(attendance: Attendance): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(attendanceService != null){
                attendanceService.addAttendanceAsync(attendance)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateAttendanceInAPI(attendance: Attendance): Boolean {
        var updated= false
        GlobalScope.launch(Dispatchers.IO) {
            if(attendanceService != null){
                attendance.id?.let { attendanceService.updateAttendanceAsync(it,attendance) }
                updated = true
            }
        }
        return updated
    }

    @WorkerThread
    fun deleteAttendanceFromAPI(attendance: Attendance): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(attendanceService != null){
                attendance.id?.let { attendanceService.deleteAttendanceAsync(it) }
                deleted = true
            }
        }
        return deleted
    }

    @WorkerThread
    fun getAttendanceFromAPI(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {

            if (attendanceService != null) {
                val response: Response<Attendance> = attendanceService.getAttendanceByIdAsync(id).await()
                val attendance = response.body()

                if (attendance != null) {
                    withContext(Dispatchers.IO) {
                        insertAttendance(attendance)
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAllAttendancesFromAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            if (attendanceService != null) {
                val response: Response<List<Attendance>> = attendanceService.getAllAttendancesAsync()!!.await()
                val attendances = response.body()

                if (attendances != null) {
                    withContext(Dispatchers.IO) {
                        attendances.forEach(this@AttendanceRepository::cache)
                    }
                }
            }
            else{

            }

        }
    }
}