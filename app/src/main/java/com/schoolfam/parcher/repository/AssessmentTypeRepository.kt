package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.assessment.Assessment
import com.schoolfam.parcher.data.assessmentType.AssessmentType
import com.schoolfam.parcher.data.assessmentType.AssessmentTypeDao

class AssessmentTypeRepository(private val assessmentTypeDao: AssessmentTypeDao) {

    fun allAssessmentsTypes(): LiveData<List<AssessmentType>> =  assessmentTypeDao.getAllAssessmentTypes()
}