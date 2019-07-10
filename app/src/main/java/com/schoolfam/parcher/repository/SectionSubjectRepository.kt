package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.SectionSubject.SectionSubject
import com.schoolfam.parcher.data.SectionSubject.SectionSubjectDao
import com.schoolfam.parcher.network.ParcherApiService

class SectionSubjectRepository(private val sectionSubjectDao: SectionSubjectDao,private val parcherApiService: ParcherApiService) {

    fun allSctionSubject(): LiveData<List<SectionSubject>> =  sectionSubjectDao.getAllSectionSubjects()

    fun sectionSubjectBySectionId(id:Long): LiveData<List<SectionSubject>> = sectionSubjectDao.getSectionSubjectBySectionId(id)
}