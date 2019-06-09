package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.subject.Subject
import com.schoolfam.parcher.data.subject.SubjectDao

class SubjectRepository(private val subjectDao: SubjectDao) {

    fun allSubjects(): LiveData<List<Subject>> =  subjectDao.getAllSubjects()
    fun subjectsById(id:Long): LiveData<Subject> = subjectDao.getSubjectById(id)
}