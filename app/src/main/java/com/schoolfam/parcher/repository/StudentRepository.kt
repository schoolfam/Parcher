package com.schoolfam.parcher.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schoolfam.parcher.data.student.Student

import com.schoolfam.parcher.data.student.StudentDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.*
import retrofit2.Response

class StudentRepository(private val studentDao: StudentDao,private val parcherApiService: ParcherApiService,private val context: Context) {

    val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

    fun allStudent(): LiveData<List<Student>> {
        GlobalScope.launch { refreshDb() }
        return studentDao.getAllStudents()
    }

    fun  findStudentsBySectionId(sectionId:Long):LiveData<List<Student>> {
        return studentDao.getStudentBySection(sectionId)
    }

    fun  findStudentsByParentId(parentId:Long):LiveData<List<Student>> {
        return studentDao.getStudentByParent(parentId)
    }

    fun studentById(id:Long): LiveData<Student> {

        return studentDao.getStudentById(id)
    }

    fun insertStudent(student: Student):Long{
        val success = addStudentAsync(student)
        GlobalScope.launch { refreshDb() }
//        Log.d("I got no post", " still none Inserted-----------------------------------------------------------------------------------------------------------------------------------------"+success)

        if(success){
            return 0L
        }
        return -1L
    }
    fun updateStudent(student: Student){


        studentDao.updateStudent(student)
    }
    fun deleteStudent(student: Student){

        studentDao.deleteStudent(student)
}
    fun deleteAllStudent(){
        studentDao.deleteAllStudents()
    }

    suspend fun refreshDb(){
        withContext(Dispatchers.IO) {
            if(networkInfo !=null && networkInfo.isConnected){
            val posts = getAllStudentAsync().value
            if(posts != null) {
                deleteAllStudent()
                studentDao.insertAllStudents(posts)
            }
            }

        }
    }

    private fun getAllStudentAsync():LiveData<List<Student>>{
        var studentsFromApi:List<Student>? = null
        val students:MutableList<Student> = emptyList<Student>().toMutableList()
        runBlocking(Dispatchers.IO) {
            val response: Response<List<Student>> = parcherApiService.getAllStudentsAsync().await()
            if(response.isSuccessful){
                studentsFromApi = response.body()

                if(studentsFromApi != null){
                    for(student:Student in studentsFromApi as List<Student>){
                        students.add(student)
                    }
                }
                else
                {
                    Log.d("I got no post", " still none")
                }
            }
            else
            {
                Log.d("Get all post: ", response.message())
            }
        }
        return MutableLiveData<List<Student>>(students)
    }

    private fun addStudentAsync(student: Student):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.addStudentAsync(student).await()
            if(response.isSuccessful){

                isSuccessful = true}
            else Log.d("Add post ASync", response.code().toString())
        }
        return isSuccessful
    }
}