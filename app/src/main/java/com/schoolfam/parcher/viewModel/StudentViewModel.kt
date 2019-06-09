package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application):AndroidViewModel(application) {

    private val studentRepository: StudentRepository
    val allStudents: LiveData<List<Student>>

    init {
        val studentDao = ParcherDatabase.getInstance(application).studentDao()
        studentRepository = StudentRepository(studentDao)
        allStudents = studentRepository.allStudent()
    }

    fun findStudentById(id:Long): LiveData<Student> {
        return studentRepository.studentById(id)
    }

    fun findStudentBySectionId(sectionId:Long): LiveData<List<Student>> {
        return studentRepository.findStudentsBySectionId(sectionId)
    }

    fun findStudentByParentId(parentId:Long): LiveData<List<Student>> {
        return studentRepository.findStudentsByParentId(parentId)
    }


    fun insertStudent(student:Student) =  viewModelScope.launch (Dispatchers.IO){
        studentRepository.insertStudent(student)
    }

    fun updateStudent(student:Student) =  viewModelScope.launch (Dispatchers.IO){
        studentRepository.updateStudent(student)
    }

    fun deleteStudent(student:Student) =  viewModelScope.launch (Dispatchers.IO){
        studentRepository.deleteStudent(student)
    }
}