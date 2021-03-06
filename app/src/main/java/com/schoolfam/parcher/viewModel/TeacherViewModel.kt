package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.teacher.Teacher
import com.schoolfam.parcher.network.DataServiceGenerator
import com.schoolfam.parcher.repository.TeacherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeacherViewModel(application: Application):AndroidViewModel(application) {

    private val teacherRepository: TeacherRepository
    val allTeachers: LiveData<List<Teacher>>
    var currentTeacher:Long = 0

    init {
        val teacherDao = ParcherDatabase.getInstance(application).teacherDao()
        val teacherService = application.let { DataServiceGenerator().createParcherApiService(it) }
        teacherRepository = TeacherRepository(teacherDao, teacherService)
        allTeachers = teacherRepository.allTeachers()
    }

    fun findTeacherByUserId(id:Long): LiveData<Teacher> {
        return teacherRepository.teacherById(id)
    }

    fun insertTeacher(teacher: Teacher) =  viewModelScope.launch (Dispatchers.IO){
        currentTeacher = teacherRepository.insertTeacher(teacher)
    }

    fun updateTeacher(teacher: Teacher) =  viewModelScope.launch (Dispatchers.IO){
        teacherRepository.updateTeacher(teacher)
    }

    fun deleteTeacher(teacher: Teacher) =  viewModelScope.launch (Dispatchers.IO){
        teacherRepository.deleteTeacher(teacher)
    }
}