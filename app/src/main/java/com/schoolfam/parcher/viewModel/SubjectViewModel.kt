package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.subject.Subject
import com.schoolfam.parcher.network.ParcherApiService
import com.schoolfam.parcher.repository.SubjectRepository

class SubjectViewModel(application: Application):AndroidViewModel(application) {

    private val subjectRepository: SubjectRepository
    val allSubjects: LiveData<List<Subject>>

    init {
        val subjectDao = ParcherDatabase.getInstance(application).subjectDao()
        val parcherApiService = ParcherApiService.getInstance()
        subjectRepository = SubjectRepository(subjectDao,parcherApiService)
        allSubjects = subjectRepository.allSubjects()
    }

    fun findSubjectById(id:Long): LiveData<Subject> {
        return subjectRepository.subjectsById(id)
    }
}