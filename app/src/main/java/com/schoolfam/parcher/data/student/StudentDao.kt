package com.schoolfam.parcher.data.student

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Query("SELECT * FROM students WHERE id = :studentId LIMIT 1")
    fun getStudentById(studentId:String): LiveData<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student):Long

    @Update
    fun updateStudent(student: Student):Int

    @Delete
    fun deleteStudent(student: Student):Int

    @Query("SELECT * FROM students ORDER BY id")
    fun getAllStudents(student: Student): LiveData<List<Student>>

}