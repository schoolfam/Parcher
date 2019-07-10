package com.schoolfam.parcher.repository

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.admin.AdminDao
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdminRepository(private val adminDao: AdminDao, private val parcherApiService: ParcherApiService,context:Context) {


    fun allAdmins(): LiveData<List<Admin>> =  adminDao.getAllAdmins()
    fun adminById(id:Long): LiveData<Admin> = adminDao.getAdminById(id)

    fun insertAdmin(admin: Admin){
        adminDao.insertAdmin(admin)
    }
    fun updateAdmin(admin: Admin){
        adminDao.updateAdmin(admin)
    }
    fun deleteAdmin(admin: Admin){
        adminDao.deleteAdmin(admin)
    }

}