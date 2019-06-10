package com.schoolfam.parcher.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.student.Student

import com.schoolfam.parcher.data.student.StudentDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class StudentRepository(private val studentDao: StudentDao, private val studentService: ParcherApiService?) {

    private fun cache(student: Student){
        studentDao.insertStudent(student)
    }

    fun insertStudent(student: Student){
        if (insertStudentToAPI(student)) {
            studentDao.insertStudent(student)
        }
    }
    fun updateStudent(student: Student){
        if (updateStudentInAPI(student)) {
            studentDao.updateStudent(student)
        }
    }
    fun deleteStudent(student: Student){
        if (deleteStudentFromAPI(student)) {
            studentDao.deleteStudent(student)
        }
    }

    fun allStudent(): LiveData<List<Student>> = studentDao.getAllStudents()

    fun  findStudentsBySectionId(sectionId:Long):LiveData<List<Student>> = studentDao.getStudentBySection(sectionId)

    fun  findStudentsByParentId(parentId:Long):LiveData<List<Student>> = studentDao.getStudentByParent(parentId)

    fun studentById(id:Long): LiveData<Student> = studentDao.getStudentById(id)

    //network functions
    @WorkerThread
    fun insertStudentToAPI(student: Student): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(studentService != null){
                studentService.addStudentAsync(student)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateStudentInAPI(student: Student): Boolean {
        var updated= false
        GlobalScope.launch(Dispatchers.IO) {
            if(studentService != null){
                student.id?.let { studentService.updateStudentAsync(it,student) }
                updated = true
            }
        }
        return updated
    }

    @WorkerThread
    fun deleteStudentFromAPI(student: Student): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(studentService != null){
                student.id?.let { studentService.deleteStudentAsync(it) }
                deleted = true
            }
        }
        return deleted
    }

    @WorkerThread
    fun getStudentFromAPI(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {

            if (studentService != null) {
                val response: Response<Student> = studentService.getStudentByIdAsync(id).await()
                val student = response.body()

                if (student != null) {
                    withContext(Dispatchers.IO) {
                        insertStudent(student)
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAllStudentsFromAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            if (studentService != null) {
                val response: Response<List<Student>> = studentService.getAllStudentsAsync()!!.await()
                val students = response.body()

                if (students != null) {
                    withContext(Dispatchers.IO) {
                        students.forEach(this@StudentRepository::cache)
                    }
                }
            }
            else{

            }

        }
    }
}