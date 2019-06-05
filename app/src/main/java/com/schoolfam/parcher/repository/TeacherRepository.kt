package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.teacher.Teacher

import com.schoolfam.parcher.data.teacher.TeacherDao

class TeacherRepository(private val teacherDao: TeacherDao) {

    fun allTeachers(): LiveData<List<Teacher>> =  teacherDao.getAllTeachers()
    fun ParentsById(id:Long): LiveData<Teacher> = teacherDao.getTeacherById(id)

    fun insertTeacher(teacher: Teacher){
        teacherDao.insertTeacher(teacher)
    }
    fun updateTeacher(teacher: Teacher){
        teacherDao.updateTeacher(teacher)
    }
    fun deleteTeacher(teacher: Teacher){
        teacherDao.deleteTeacher(teacher)
    }
}