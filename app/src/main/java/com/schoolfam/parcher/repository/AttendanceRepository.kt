package com.schoolfam.parcher.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.attendance.AttendanceDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.*
import retrofit2.Response
import java.util.*

class AttendanceRepository(private val attendanceDao: AttendanceDao,private val parcherApiService: ParcherApiService,private val context: Context) {

    val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

    fun allAttendance(): LiveData<List<Attendance>> {
        GlobalScope.launch { refreshDb() }
        return attendanceDao.getAllAttendances()
    }

    fun allAttendanceByDateAndSection(date:Long,sectionId:Long): LiveData<List<Attendance>> =  attendanceDao.getAttendanceByDateAndSection(date,sectionId)

    fun attendanceByDateAndStudent(date:Long,studentId:Long): LiveData<Attendance> =  attendanceDao.getAttendanceByDateAndStudent(date,studentId)

    fun attendanceById(id:Long): LiveData<Attendance> = attendanceDao.getAttendanceById(id)

    fun insertAttendance(attendance: Attendance):Long{
        var success =false
        if(networkInfo !=null && networkInfo.isConnected){
         success = addAttendanceAsync(attendance)}
        GlobalScope.launch { refreshDb() }
//        Log.d("I got no post", " still none Inserted-----------------------------------------------------------------------------------------------------------------------------------------"+success)

        if(success){
            return 0L
        }
        return -1L
    }
    fun updateAttendance(attendance: Attendance){
        attendanceDao.updateAttendance(attendance)
    }
    fun deleteAttendance(attendance: Attendance):Long{
        var success =false
        if(networkInfo !=null && networkInfo.isConnected){
            success = deleteAttendanceAsync(attendance)
        }

        GlobalScope.launch { refreshDb() }
        if(success){
            return 0L
        }
        return -1L
    }

    fun deleteAllParent(){
        attendanceDao.deleteAllAttendances()
    }

    suspend fun refreshDb(){
        withContext(Dispatchers.IO) {
            if(networkInfo !=null && networkInfo.isConnected){
            val attendance = getAllAttendanceAsync().value
            if(attendance != null) {
                deleteAllParent()
                attendanceDao.insertAllAttendances(attendance)
            }
            }

        }
    }

    private fun getAllAttendanceAsync():LiveData<List<Attendance>>{
        var attendanceFromApi:List<Attendance>? = null
        val attendances:MutableList<Attendance> = emptyList<Attendance>().toMutableList()
        runBlocking(Dispatchers.IO) {
            val response: Response<List<Attendance>> = parcherApiService.getAllAttendancesAsync().await()
            if(response.isSuccessful){
                attendanceFromApi = response.body()

                if(attendanceFromApi != null){
                    for(attendance:Attendance in attendanceFromApi as List<Attendance>){
                        attendances.add(attendance)
                    }
                }
                else
                {
                    Log.d("I got no Attendance", " still none")
                }
            }
            else
            {
                Log.d("Get all Attendances: ", response.message())
            }
        }
        return MutableLiveData<List<Attendance>>(attendances)
    }

    private fun addAttendanceAsync(attendance: Attendance):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.addAttendanceAsync(attendance).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Add Attendance ASync", response.code().toString())
        }
        return isSuccessful
    }

    private fun deleteAttendanceAsync(attendance: Attendance):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.deleteAttendanceAsync(attendance.id!!).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Add Attendance ASync", response.code().toString())
        }
        return isSuccessful
    }
}