package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.student.Student

import com.schoolfam.parcher.data.student.StudentDao

class StudentRepository(private val studentDao: StudentDao) {

    fun allStudent(): LiveData<List<Student>> = studentDao.getAllStudents()

    fun  findStudentsById(sectionId:Long):LiveData<List<Student>> = studentDao.getStudentBySection(sectionId)

    fun studentById(id:Long): LiveData<Student> = studentDao.getStudentById(id)

    fun insertStudent(student: Student){
        studentDao.insertStudent(student)
    }
    fun updateAdmin(student: Student){
        studentDao.updateStudent(student)
    }
    fun deleteAdmin(student: Student){
        studentDao.deleteStudent(student)
}
}