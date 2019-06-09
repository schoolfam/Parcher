package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.assessmentType.AssessmentType
import com.schoolfam.parcher.repository.AssessmentTypeRepository


class AssessmentTypeViewModel(application: Application):AndroidViewModel(application) {

    private val assessmentTypeRepository: AssessmentTypeRepository
    val allAssessmentTypes: LiveData<List<AssessmentType>>

    init {
        val assessmentTypeDao = ParcherDatabase.getInstance(application).assessmentTypeDao()
        assessmentTypeRepository = AssessmentTypeRepository(assessmentTypeDao)
        allAssessmentTypes = assessmentTypeRepository.allAssessmentsTypes()
    }
}