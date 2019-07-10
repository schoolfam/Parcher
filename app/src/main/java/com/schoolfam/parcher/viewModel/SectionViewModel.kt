package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.network.ParcherApiService
import com.schoolfam.parcher.repository.SectionRepository

class SectionViewModel(application: Application):AndroidViewModel(application) {

    private val sectionRepository: SectionRepository
    val allSection: LiveData<List<Section>>

    init {
        val sectionDao = ParcherDatabase.getInstance(application).sectionDao()
        val parcherApiService = ParcherApiService.getInstance()
        sectionRepository = SectionRepository(sectionDao,parcherApiService)
        allSection = sectionRepository.allSection()
    }
}