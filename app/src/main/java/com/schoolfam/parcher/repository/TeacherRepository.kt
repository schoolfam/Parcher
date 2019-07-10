package com.schoolfam.parcher.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schoolfam.parcher.data.teacher.Teacher

import com.schoolfam.parcher.data.teacher.TeacherDao
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.*
import retrofit2.Response

class TeacherRepository(private val teacherDao: TeacherDao,private val parcherApiService: ParcherApiService,private val context: Context) {

    val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

    fun allTeachers(): LiveData<List<Teacher>> {
        GlobalScope.launch { refreshDb() }
        return teacherDao.getAllTeachers()
    }
    fun teacherById(id:Long): LiveData<Teacher> {
        GlobalScope.launch { refreshDb() }
        return teacherDao.getTeacherById(id)
    }

    fun insertTeacher(teacher: Teacher):Long{
        val success = addTeacherAsync(teacher)
        GlobalScope.launch { refreshDb() }
        Log.d("I got no post", " still none Inserted-----------------------------------------------------------------------------------------------------------------------------------------"+success)

        if(success){
            return 0L
        }
        return -1L

    }
    fun updateTeacher(teacher: Teacher){
        GlobalScope.launch { refreshDb() }
        teacherDao.updateTeacher(teacher)
    }
    fun deleteTeacher(teacher: Teacher){

        GlobalScope.launch { refreshDb() }
        teacherDao.deleteTeacher(teacher)
    }

    fun deleteAllTeachers(){
        teacherDao.deleteAllTeachers()
    }

    suspend fun refreshDb(){
        withContext(Dispatchers.IO) {
            if(networkInfo !=null && networkInfo.isConnected){
                val teachers = getAllTeachersAsync().value
                if(teachers != null) {
                    deleteAllTeachers()
                    teacherDao.insertAllTeachers(teachers)
                }
            }

        }
    }

    private fun getAllTeachersAsync():LiveData<List<Teacher>>{
        var teachersFromApi:List<Teacher>? = null
        val teachers:MutableList<Teacher> = emptyList<Teacher>().toMutableList()
        runBlocking(Dispatchers.IO) {
            val response: Response<List<Teacher>> = parcherApiService.getAllTeachersAsync().await()
            if(response.isSuccessful){
                teachersFromApi = response.body()

                if(teachersFromApi != null){
                    for(teacher:Teacher in teachersFromApi as List<Teacher>){
                        teachers.add(teacher)
                        Log.d("I got no post", " still none Teaxher-----------------------------------------------------------------------------------------------------------------------------------------"+teachers.size)

                    }
                }
                else
                {
                    Log.d("I got no teachers", " still none")
                }
            }
            else
            {
                Log.d("Get all teachers: ", response.message())
            }
        }
        return MutableLiveData<List<Teacher>>(teachers)
    }

    private fun addTeacherAsync(teacher: Teacher):Boolean{
        var isSuccessful = false
        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.addTeacherAsync(teacher).await()
            if(response.isSuccessful){
                isSuccessful = true
                 }
            else Log.d("Add Teacher ASync", response.code().toString())
        }
        return isSuccessful
    }
}