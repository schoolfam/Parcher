package com.schoolfam.parcher.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.teacher.Teacher

import com.schoolfam.parcher.data.teacher.TeacherDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TeacherRepository(private val teacherDao: TeacherDao, private val teacherService: ParcherApiService?) {

    private fun cache(teacher: Teacher){
        teacherDao.insertTeacher(teacher)
    }

    fun insertTeacher(teacher: Teacher):Long{
        if (insertTeacherToAPI(teacher)) {
            teacherDao.insertTeacher(teacher)
        }
        return teacherDao.insertTeacher(teacher)
    }

    fun updateTeacher(teacher: Teacher){
        if (updateTeacherInAPI(teacher)) {
            teacherDao.updateTeacher(teacher)
        }
    }

    fun deleteTeacher(teacher: Teacher){
        if (deleteTeacherFromAPI(teacher)) {
            teacherDao.deleteTeacher(teacher)
        }
    }

    fun allTeachers(): LiveData<List<Teacher>> =  teacherDao.getAllTeachers()

    fun teacherById(id:Long): LiveData<Teacher> = teacherDao.getTeacherById(id)

    //network functions
    @WorkerThread
    fun insertTeacherToAPI(teacher: Teacher): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(teacherService != null){
                teacherService.addTeacherAsync(teacher)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateTeacherInAPI(teacher: Teacher): Boolean {
        var updated= false
        GlobalScope.launch(Dispatchers.IO) {
            if(teacherService != null){
                teacher.id?.let { teacherService.updateTeacherAsync(it,teacher) }
                updated = true
            }
        }
        return updated
    }

    @WorkerThread
    fun deleteTeacherFromAPI(teacher: Teacher): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(teacherService != null){
                teacher.id?.let { teacherService.deleteTeacherAsync(it) }
                deleted = true
            }
        }
        return deleted
    }

    @WorkerThread
    fun getTeacherFromAPI(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {

            if (teacherService != null) {
                val response: Response<Teacher> = teacherService.getTeacherByIdAsync(id).await()
                val student = response.body()

                if (student != null) {
                    withContext(Dispatchers.IO) {
                        insertTeacher(student)
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAllTeachersFromAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            if (teacherService != null) {
                val response: Response<List<Teacher>> = teacherService.getAllTeachersAsync()!!.await()
                val teachers = response.body()

                if (teachers != null) {
                    withContext(Dispatchers.IO) {
                        teachers.forEach(this@TeacherRepository::cache)
                    }
                }
            }
            else{

            }

        }
    }

}