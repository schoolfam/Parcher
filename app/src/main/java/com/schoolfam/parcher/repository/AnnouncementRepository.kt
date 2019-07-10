package com.schoolfam.parcher.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.data.announcement.AnnouncementDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.*
import retrofit2.Response

class AnnouncementRepository(private val announcementDao: AnnouncementDao,private val parcherApiService: ParcherApiService,private val context: Context) {

    val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

    fun allAnnouncement(): LiveData<List<Announcement>> {
        GlobalScope.launch { refreshDb() }
        return announcementDao.getAllAnnouncement()
    }
    fun announcementById(id:Long): LiveData<Announcement> = announcementDao.getAnnouncementById(id)

    fun insertAnnouncement(announcement: Announcement):Long{
        val success = addAnnouncementAsync(announcement)
        GlobalScope.launch { refreshDb() }
//        Log.d("I got no post", " still none Inserted-----------------------------------------------------------------------------------------------------------------------------------------"+success)

        if(success){
            return 0L
        }
        return -1L
    }
    fun updateAnnouncement(announcement: Announcement):Long{
        var success = false
        if(networkInfo !=null && networkInfo.isConnected){
         success = updateAnnouncementAsync(announcement)}
        GlobalScope.launch { refreshDb() }
//        Log.d("I got no post", " still none Inserted-----------------------------------------------------------------------------------------------------------------------------------------"+success)

        if(success){
            return 0L
        }
        return -1L
    }
    fun deleteAnnouncement(announcement: Announcement):Long{
        var success =false
        if(networkInfo !=null && networkInfo.isConnected){
            success = deleteAnnouncementAsync(announcement)
        }

        GlobalScope.launch { refreshDb() }
        if(success){
            return 0L
        }
        return -1L
    }

    fun deleteAllParent(){
        announcementDao.deleteAllAnnouncements()
    }

    suspend fun refreshDb(){
        withContext(Dispatchers.IO) {
            if(networkInfo !=null && networkInfo.isConnected){
            val announcement = getAllAnnouncementAsync().value
            if(announcement != null) {
                deleteAllParent()
                announcementDao.insertAllAnnouncements(announcement)
            }
            }

        }
    }

    private fun getAllAnnouncementAsync():LiveData<List<Announcement>>{
        var announcementFromApi:List<Announcement>? = null
        val announcements:MutableList<Announcement> = emptyList<Announcement>().toMutableList()
        runBlocking(Dispatchers.IO) {
            val response: Response<List<Announcement>> = parcherApiService.getAllAnnouncementsAsync().await()
            if(response.isSuccessful){
                announcementFromApi = response.body()

                if(announcementFromApi != null){
                    for(announcement:Announcement in announcementFromApi as List<Announcement>){
                        announcements.add(announcement)
                    }
                }
                else
                {
                    Log.d("I got no announcement", " still none")
                }
            }
            else
            {
                Log.d("Get all announcements: ", response.message())
            }
        }
        return MutableLiveData<List<Announcement>>(announcements)
    }

    private fun addAnnouncementAsync(announcement: Announcement):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.addAnnouncementAsync(announcement).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Add Announcement ASync", response.code().toString())
        }
        return isSuccessful
    }

    private fun updateAnnouncementAsync(announcement: Announcement):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.updateAnnouncementAsync(announcement).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Add Announcement ASync", response.code().toString())
        }
        return isSuccessful
    }

    private fun deleteAnnouncementAsync(announcement: Announcement):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.deleteAnnouncementAsync(announcement.id!!).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Add Announcement ASync", response.code().toString())
        }
        return isSuccessful
    }
}