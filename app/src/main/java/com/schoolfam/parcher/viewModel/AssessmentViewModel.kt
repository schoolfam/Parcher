package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.assessment.Assessment
import com.schoolfam.parcher.network.ParcherApiService
import com.schoolfam.parcher.repository.AssessmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssessmentViewModel (application: Application):AndroidViewModel(application) {
    private val assessmentRepository: AssessmentRepository
    val allAssessments: LiveData<List<Assessment>>

    init {
        val assessmentDao = ParcherDatabase.getInstance(application).assessmentDao()
        val parcherApiService = ParcherApiService.getInstance()
        assessmentRepository = AssessmentRepository(assessmentDao,parcherApiService,application)
        allAssessments = assessmentRepository.allAssessments()
    }

    fun findAssessmentById(id:Long): LiveData<Assessment> {
        return assessmentRepository.assessmentById(id)
    }

    fun findAssessmentByStudentIdAndAssessmentTypeId(assessmentTypeId:Long,studentId:Long): LiveData<Assessment> {
        return assessmentRepository.assessmentByStudentIdAndAssessmentTypeId(assessmentTypeId,studentId)
    }

    fun findAssessmentByStudentIdAndSubjectId(subjectId:Long,studentId:Long): LiveData<List<Assessment>> {
        return assessmentRepository.assessmentByStudentIdAndSubjectId(subjectId,studentId)
    }


    fun insertAssessment(assessment:Assessment) =  viewModelScope.launch (Dispatchers.IO){
        assessmentRepository.insertAssessment(assessment)
    }

    fun updateAssessment(assessment:Assessment) =  viewModelScope.launch (Dispatchers.IO){
        assessmentRepository.updateAssessment(assessment)
    }

    fun deleteAssessment(assessment:Assessment) =  viewModelScope.launch (Dispatchers.IO){
        assessmentRepository.deleteAssessment(assessment)
    }
}