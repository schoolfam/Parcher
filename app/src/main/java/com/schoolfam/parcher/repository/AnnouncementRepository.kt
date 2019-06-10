package com.schoolfam.parcher.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.data.announcement.AnnouncementDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AnnouncementRepository(private val announcementDao: AnnouncementDao, private val announcementService: ParcherApiService?) {

    private fun cache(announcement: Announcement){
        announcementDao.insertAnnouncement(announcement)
    }

    fun insertAnnouncement(announcement: Announcement){
        if (insertAnnouncementToAPI(announcement)) {
            announcementDao.insertAnnouncement(announcement)
        }
    }
    fun updateAnnouncement(announcement: Announcement){
        if (updateAnnouncementInAPI(announcement)) {
            announcementDao.updateAnnouncement(announcement)
        }
    }
    fun deleteAnnouncement(announcement: Announcement){
        if (deleteAnnouncementFromAPI(announcement)) {
            announcementDao.deleteAnnouncement(announcement)
        }
    }

    fun allAnnouncement(): LiveData<List<Announcement>> =  announcementDao.getAllAnnouncement()

    fun announcementById(id:Long): LiveData<Announcement> = announcementDao.getAnnouncementById(id)


    //network functions
    @WorkerThread
    fun insertAnnouncementToAPI(announcement: Announcement): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(announcementService != null){
                announcementService.addAnnouncementAsync(announcement)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateAnnouncementInAPI(announcement: Announcement): Boolean {
        var updated= false
        GlobalScope.launch(Dispatchers.IO) {
            if(announcementService != null){
                announcement.id?.let { announcementService.updateAnnouncementAsync(it, announcement) }
                updated = true
            }
        }
        return updated
    }

    @WorkerThread
    fun deleteAnnouncementFromAPI(announcement: Announcement): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(announcementService != null){
                announcement.id?.let { announcementService.deleteAnnouncementAsync(it) }
                deleted = true
            }
        }
        return deleted
    }

    @WorkerThread
    fun getAnnouncementFromAPI(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {

            if (announcementService != null) {
                val response: Response<Announcement> = announcementService.getAnnouncementByIdAsync(id).await()
                val announcement = response.body()

                if (announcement != null) {
                    withContext(Dispatchers.IO) {
                        insertAnnouncement(announcement)
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAllAnnouncementsFromAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            if (announcementService != null) {
                val response: Response<List<Announcement>> = announcementService.getAllAnnouncementsAsync()!!.await()
                val announcements = response.body()

                if (announcements != null) {
                    withContext(Dispatchers.IO) {
                        announcements.forEach(this@AnnouncementRepository::cache)
                    }
                }
            }
            else{

            }
        }
    }
}