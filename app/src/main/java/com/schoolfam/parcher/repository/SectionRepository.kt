package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.section.SectionDao
import com.schoolfam.parcher.network.ParcherApiService

class SectionRepository(private val sectionDao: SectionDao) {

    fun allSection(): LiveData<List<Section>> =  sectionDao.getAllSections()
}