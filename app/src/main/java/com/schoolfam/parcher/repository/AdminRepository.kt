package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.admin.AdminDao
import com.schoolfam.parcher.data.user.User

class AdminRepository(private val adminDao: AdminDao) {

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