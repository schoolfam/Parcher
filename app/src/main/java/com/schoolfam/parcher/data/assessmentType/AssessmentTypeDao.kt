package com.schoolfam.parcher.data.assessmentType

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AssessmentTypeDao {

    @Query("SELECT * FROM assessmentTypes WHERE id = :assessmentTypeId LIMIT 1")
    fun getAssessmentTypeById(assessmentTypeId:String): LiveData<AssessmentType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAssessmentType(admin: AssessmentType):Long

    @Update
    fun updateAssessmentType(assessmentType: AssessmentType):Int

    @Delete
    fun deleteAssessmentType(assessmentType: AssessmentType):Int

    @Query("SELECT * FROM assessmentTypes ORDER BY id")
    fun getAllAssessmentTypes(assessmentType: AssessmentType): LiveData<List<AssessmentType>>

}