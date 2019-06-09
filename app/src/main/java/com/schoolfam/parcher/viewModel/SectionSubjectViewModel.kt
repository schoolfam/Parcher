package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.SectionSubject.SectionSubject
import com.schoolfam.parcher.repository.SectionSubjectRepository


class SectionSubjectViewModel(application: Application):AndroidViewModel(application) {

    private val sectionSubjectRepository: SectionSubjectRepository
    val allSectionSubject: LiveData<List<SectionSubject>>

    init {
        val sectionSubjectDao = ParcherDatabase.getInstance(application).sectionsubjectDao()
        sectionSubjectRepository = SectionSubjectRepository(sectionSubjectDao)
        allSectionSubject = sectionSubjectRepository.allSctionSubject()
    }

    fun findSectionSubjectBySectionId(id:Long): LiveData<List<SectionSubject>> {
        return sectionSubjectRepository.sectionSubjectBySectionId(id)
    }
}