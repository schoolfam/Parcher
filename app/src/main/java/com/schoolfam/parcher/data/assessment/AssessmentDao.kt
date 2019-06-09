package com.schoolfam.parcher.data.assessment

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AssessmentDao {

    @Query("SELECT * FROM assessments WHERE id = :assessmentId LIMIT 1")
    fun getAssessmentById(assessmentId:Long): LiveData<Assessment>

    @Query("SELECT * FROM assessments WHERE assessment_type_id = :assessmenttypeId AND student_id = :studenId LIMIT 1")
    fun getAssessmentByStudentIdAndAssessementTypeId(assessmenttypeId:Long,studenId:Long): LiveData<Assessment>

    @Query("SELECT * FROM assessments WHERE subject_id = :subjectId AND student_id = :studenId")
    fun getAssessmentByStudentIdAndSubjectId(subjectId:Long,studenId:Long): LiveData<List<Assessment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAssessment(assessment: Assessment):Long

    @Update
    fun updateAssessment(assessment: Assessment):Int

    @Delete
    fun deleteAssessment(assessment: Assessment):Int

    @Query("SELECT * FROM assessments ORDER BY id")
    fun getAllAssessments(): LiveData<List<Assessment>>

}