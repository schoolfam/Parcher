package com.schoolfam.parcher.data.announcement

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface AnnouncementDao {

    @Query("SELECT * FROM announcement WHERE id = :announcementId LIMIT 1")
    fun getAnnouncementById(announcementId:Long): LiveData<Announcement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnnouncement(announcement: Announcement):Long

    @Update
    fun updateAnnouncement(announcement: Announcement):Int

    @Delete
    fun deleteAnnouncement(announcement: Announcement):Int

    @Query("SELECT * FROM announcement ORDER BY id")
    fun getAllAnnouncement(): LiveData<List<Announcement>>
}