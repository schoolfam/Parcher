package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.assessment.Assessment
import com.schoolfam.parcher.data.assessmentType.AssessmentType
import com.schoolfam.parcher.data.assessmentType.AssessmentTypeDao
import com.schoolfam.parcher.network.ParcherApiService

class AssessmentTypeRepository(private val assessmentTypeDao: AssessmentTypeDao,private val parcherApiService: ParcherApiService) {

    fun allAssessmentsTypes(): LiveData<List<AssessmentType>> =  assessmentTypeDao.getAllAssessmentTypes()
}