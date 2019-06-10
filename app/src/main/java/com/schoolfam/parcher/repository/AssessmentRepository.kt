package com.schoolfam.parcher.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.assessment.Assessment

import com.schoolfam.parcher.data.assessment.AssessmentDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AssessmentRepository(private val assessmentDao: AssessmentDao, private val assessmentService: ParcherApiService?) {

    private fun cache(assessment: Assessment){
        assessmentDao.insertAssessment(assessment)
    }

    fun insertAssessment(assessment: Assessment){
        if (insertAssessmentToAPI(assessment)) {
            assessmentDao.insertAssessment(assessment)
        }
    }
    fun updateAssessment(assessment: Assessment){
        if (updateAssessmentInAPI(assessment)) {
            assessmentDao.updateAssessment(assessment)
        }
    }

    fun deleteAssessment(assessment: Assessment){
        if (deleteAssessmentFromAPI(assessment)) {
            assessmentDao.deleteAssessment(assessment)
        }
    }

    fun allAssessments(): LiveData<List<Assessment>> =  assessmentDao.getAllAssessments()

    fun assessmentById(id:Long): LiveData<Assessment> = assessmentDao.getAssessmentById(id)

    fun assessmentByStudentIdAndAssessmentTypeId(assessmentTypeId:Long, studentId:Long): LiveData<Assessment> = assessmentDao.getAssessmentByStudentIdAndAssessementTypeId(assessmentTypeId,studentId)

    fun assessmentByStudentIdAndSubjectId(subjectId:Long,studentId:Long): LiveData<List<Assessment>> = assessmentDao.getAssessmentByStudentIdAndSubjectId(subjectId,studentId)


    //network functions
    @WorkerThread
    fun insertAssessmentToAPI(assessment: Assessment): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(assessmentService != null){
                assessmentService.addAssessmentAsync(assessment)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateAssessmentInAPI(assessment: Assessment): Boolean {
        var updated= false
        GlobalScope.launch(Dispatchers.IO) {
            if(assessmentService != null){
                assessment.id?.let { assessmentService.updateAssessmentAsync(it, assessment) }
                updated = true
            }
        }
        return updated
    }

    @WorkerThread
    fun deleteAssessmentFromAPI(assessment: Assessment): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(assessmentService != null){
                assessment.id?.let { assessmentService.deleteAssessmentAsync(it) }
                deleted = true
            }
        }
        return deleted
    }

    @WorkerThread
    fun getAssessmentFromAPI(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {

            if (assessmentService != null) {
                val response: Response<Assessment> = assessmentService.getAssessmentByIdAsync(id).await()
                val assessment = response.body()

                if (assessment != null) {
                    withContext(Dispatchers.IO) {
                        insertAssessment(assessment)
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAllAssessmentsFromAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            if (assessmentService != null) {
                val response: Response<List<Assessment>> = assessmentService.getAllAssessmentsAsync()!!.await()
                val assessments = response.body()

                if (assessments != null) {
                    withContext(Dispatchers.IO) {
                        assessments.forEach(this@AssessmentRepository::cache)
                    }
                }
            }
            else{

            }

        }
    }
}