package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.assessment.Assessment

import com.schoolfam.parcher.data.assessment.AssessmentDao

class AssessmentRepository(private val assessmentDao: AssessmentDao) {


    fun allAssessments(): LiveData<List<Assessment>> =  assessmentDao.getAllAssessments()


    fun assessmentById(id:Long): LiveData<Assessment> = assessmentDao.getAssessmentById(id)

    fun assessmentByStudentIdAndAssessmentTypeId(assessmentTypeId:Long,studentId:Long): LiveData<Assessment> = assessmentDao.getAssessmentByStudentIdAndAssessementTypeId(assessmentTypeId,studentId)

    fun assessmentByStudentIdAndSubjectId(subjectId:Long,studentId:Long): LiveData<List<Assessment>> = assessmentDao.getAssessmentByStudentIdAndSubjectId(subjectId,studentId)


    fun insertAssessment(assessment: Assessment){
        assessmentDao.insertAssessment(assessment)
    }
    fun updateAssessment(assessment: Assessment){
        assessmentDao.updateAssessment(assessment)
    }

    fun deleteAssessment(assessment: Assessment){
        assessmentDao.deleteAssessment(assessment)
    }
}