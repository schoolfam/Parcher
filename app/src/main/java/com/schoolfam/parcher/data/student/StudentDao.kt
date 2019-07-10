package com.schoolfam.parcher.data.student

import androidx.lifecycle.LiveData
import androidx.room.*
import com.schoolfam.parcher.data.user.User

@Dao
interface StudentDao {

    @Query("SELECT * FROM students WHERE id = :studentId LIMIT 1")
    fun getStudentById(studentId:Long): LiveData<Student>

    @Query("SELECT * FROM students WHERE section_id = :sectionId ")
    fun getStudentBySection(sectionId:Long): LiveData<List<Student>>

    @Query("SELECT * FROM students WHERE parent_id = :parentId ")
    fun getStudentByParent(parentId:Long): LiveData<List<Student>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student):Long

    @Update
    fun updateStudent(student: Student):Int

    @Delete
    fun deleteStudent(student: Student):Int

    @Query("SELECT * FROM students ORDER BY id")
    fun getAllStudents(): LiveData<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStudents(students: List<Student>)

    @Query("DELETE FROM students")
    fun deleteAllStudents()

}