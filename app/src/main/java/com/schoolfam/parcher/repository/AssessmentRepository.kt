package com.schoolfam.parcher.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schoolfam.parcher.data.assessment.Assessment

import com.schoolfam.parcher.data.assessment.AssessmentDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.*
import retrofit2.Response

class AssessmentRepository(private val assessmentDao: AssessmentDao,private val parcherApiService: ParcherApiService,private val context: Context) {

    val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo



    fun allAssessments(): LiveData<List<Assessment>> {
        GlobalScope.launch { refreshDb() }
        return assessmentDao.getAllAssessments()
    }


    fun assessmentById(id:Long): LiveData<Assessment> = assessmentDao.getAssessmentById(id)

    fun assessmentByStudentIdAndAssessmentTypeId(assessmentTypeId:Long,studentId:Long): LiveData<Assessment> = assessmentDao.getAssessmentByStudentIdAndAssessementTypeId(assessmentTypeId,studentId)

    fun assessmentByStudentIdAndSubjectId(subjectId:Long,studentId:Long): LiveData<List<Assessment>> = assessmentDao.getAssessmentByStudentIdAndSubjectId(subjectId,studentId)


    fun insertAssessment(assessment: Assessment):Long{
        val success = addAssessmentAsync(assessment)
        GlobalScope.launch { refreshDb() }
//        Log.d("I got no post", " still none Inserted-----------------------------------------------------------------------------------------------------------------------------------------"+success)

        if(success){
            return 0L
        }
        return -1L

    }
    fun updateAssessment(assessment: Assessment):Long{
        var success = false
        if(networkInfo !=null && networkInfo.isConnected){
         success = updateAssessmentAsync(assessment)
        }
        GlobalScope.launch { refreshDb() }
        if(success){
            return 0L
        }
        return -1L
    }

    fun deleteAssessment(assessment: Assessment):Long{
        var success =false
        if(networkInfo !=null && networkInfo.isConnected){
            success = deleteAssessmentAsync(assessment)
        }

        GlobalScope.launch { refreshDb() }
        if(success){
            return 0L
        }
        return -1L

    }

    fun deleteAllAssessment(){
        assessmentDao.deleteAllAssessment()
    }

    suspend fun refreshDb(){
        withContext(Dispatchers.IO) {
            if(networkInfo !=null && networkInfo.isConnected){
            val assessments = getAllAssessmentAsync().value
            if(assessments != null) {
                deleteAllAssessment()
                assessmentDao.insertAllAssessment(assessments)
            }
            }

        }
    }

    private fun getAllAssessmentAsync():LiveData<List<Assessment>>{
        var assessmentFromApi:List<Assessment>? = null
        val assessments:MutableList<Assessment> = emptyList<Assessment>().toMutableList()
        runBlocking(Dispatchers.IO) {
            val response: Response<List<Assessment>> = parcherApiService.getAllAssessmentsAsync().await()
            if(response.isSuccessful){
                assessmentFromApi = response.body()

                if(assessmentFromApi != null){
                    for(assessment:Assessment in assessmentFromApi as List<Assessment>){
                        assessments.add(assessment)
                    }
                }
                else
                {
                    Log.d("I got no Assessment", " still none")
                }
            }
            else
            {
                Log.d("Get all Assessment: ", response.message())
            }
        }
        return MutableLiveData<List<Assessment>>(assessments)
    }

    private fun addAssessmentAsync(assessment: Assessment):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.addAssessmentAsync(assessment).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Add Parent ASync", response.code().toString())
        }
        return isSuccessful
    }

    private fun updateAssessmentAsync(assessment: Assessment):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.updateAssessmentAsync(assessment).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Upddate Parent ASync", response.code().toString())
        }
        return isSuccessful
    }

    private fun deleteAssessmentAsync(assessment: Assessment):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.deleteAssessmentAsync(assessment.id!!).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Delete Parent ASync", response.code().toString())
        }
        return isSuccessful
    }
}