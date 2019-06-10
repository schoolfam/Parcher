package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.data.announcement.AnnouncementDao

class AnnouncementRepository(private val announcementDao: AnnouncementDao) {

    fun allAnnouncement(): LiveData<List<Announcement>> =  announcementDao.getAllAnnouncement()
    fun announcementById(id:Long): LiveData<Announcement> = announcementDao.getAnnouncementById(id)

    fun insertAnnouncement(announcement: Announcement){
        announcementDao.insertAnnouncement(announcement)
    }
    fun updateAnnouncement(announcement: Announcement){
        announcementDao.updateAnnouncement(announcement)
    }
    fun deleteAnnouncement(announcement: Announcement){
        announcementDao.deleteAnnouncement(announcement)
    }
}