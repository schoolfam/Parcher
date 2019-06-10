package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.repository.AnnouncementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnnouncementViewModel(application: Application):AndroidViewModel(application) {

    private val announcementRepository: AnnouncementRepository
    val allAnnoncements: LiveData<List<Announcement>>

    init {
        val announcementDao = ParcherDatabase.getInstance(application).announcementDao()
        announcementRepository = AnnouncementRepository(announcementDao)
        allAnnoncements = announcementRepository.allAnnouncement()
    }

    fun findAdminById(id:Long): LiveData<Announcement> {
        return announcementRepository.announcementById(id)
    }


    fun insertAnnouncement(announcement:Announcement) =  viewModelScope.launch (Dispatchers.IO){
        announcementRepository.insertAnnouncement(announcement)
    }

    fun updateAnnouncement(announcement:Announcement) =  viewModelScope.launch (Dispatchers.IO){
        announcementRepository.updateAnnouncement(announcement)
    }

    fun deleteAnnouncement(announcement:Announcement) =  viewModelScope.launch (Dispatchers.IO){
        announcementRepository.deleteAnnouncement(announcement)
    }
}