package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.student.Student

import com.schoolfam.parcher.data.student.StudentDao

class StudentRepository(private val studentDao: StudentDao) {

    fun allStudent(): LiveData<List<Student>> = studentDao.getAllStudents()

    fun  findStudentsBySectionId(sectionId:Long):LiveData<List<Student>> = studentDao.getStudentBySection(sectionId)

    fun studentById(id:Long): LiveData<Student> = studentDao.getStudentById(id)

    fun insertStudent(student: Student){
        studentDao.insertStudent(student)
    }
    fun updateStudent(student: Student){
        studentDao.updateStudent(student)
    }
    fun deleteStudent(student: Student){
        studentDao.deleteStudent(student)
}
}