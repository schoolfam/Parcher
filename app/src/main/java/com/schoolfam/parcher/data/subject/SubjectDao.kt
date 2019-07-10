package com.schoolfam.parcher.data.subject

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubjectDao {

    @Query("SELECT * FROM subjects WHERE id = :subjectId LIMIT 1")
    fun getSubjectById(subjectId:Long): LiveData<Subject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubject(subject: Subject):Long

    @Update
    fun updateSubject(subject: Subject):Int

    @Delete
    fun deleteSubject(subject: Subject):Int

    @Query("SELECT * FROM subjects ORDER BY id")
    fun getAllSubjects(): LiveData<List<Subject>>

}