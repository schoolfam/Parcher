package com.schoolfam.parcher.data.teacher

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TeacherDao {

    @Query("SELECT * FROM teachers WHERE id = :teacherId LIMIT 1")
    fun getTeacherById(teacherId:Long): LiveData<Teacher>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeacher(teacher: Teacher):Long

    @Update
    fun updateTeacher(teacher: Teacher):Int

    @Delete
    fun deleteTeacher(teacher: Teacher):Int

    @Query("SELECT * FROM teachers ORDER BY id")
    fun getAllTeachers(): LiveData<List<Teacher>>

}