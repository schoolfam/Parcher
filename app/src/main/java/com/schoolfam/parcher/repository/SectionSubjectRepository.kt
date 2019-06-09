package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.SectionSubject.SectionSubject
import com.schoolfam.parcher.data.SectionSubject.SectionSubjectDao

class SectionSubjectRepository(private val sectionSubjectDao: SectionSubjectDao) {

    fun allSctionSubject(): LiveData<List<SectionSubject>> =  sectionSubjectDao.getAllSectionSubjects()

    fun sectionSubjectBySectionId(id:Long): LiveData<List<SectionSubject>> = sectionSubjectDao.getSectionSubjectBySectionId(id)
}