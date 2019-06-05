package com.schoolfam.parcher.data.assessment

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AssessmentDao {

    @Query("SELECT * FROM assessments WHERE id = :assessmentId LIMIT 1")
    fun getAssessmentById(assessmentId:Long): LiveData<Assessment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAssessment(assessment: Assessment):Long

    @Update
    fun updateAssessment(assessment: Assessment):Int

    @Delete
    fun deleteAssessment(assessment: Assessment):Int

    @Query("SELECT * FROM assessments ORDER BY id")
    fun getAllAssessments(): LiveData<List<Assessment>>

}