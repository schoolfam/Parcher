package com.schoolfam.parcher.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.admin.AdminDao
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AdminRepository(private val adminDao: AdminDao, private val adminService: ParcherApiService?) {

    private fun cache(admin: Admin){
        adminDao.insertAdmin(admin)
    }

    fun insertAdmin(admin: Admin){
        if (insertAdminToAPI(admin)){
            adminDao.insertAdmin(admin)
        }
    }

    fun updateAdmin(admin: Admin){
        if (updateAdminInAPI(admin)){
            adminDao.updateAdmin(admin)
        }
    }

    fun deleteAdmin(admin: Admin){
        if (deleteAdminFromAPI(admin)) {
            adminDao.deleteAdmin(admin)
        }
    }

    fun allAdmins(): LiveData<List<Admin>> =  adminDao.getAllAdmins()

    fun adminById(id:Long): LiveData<Admin> = adminDao.getAdminById(id)


    //network functions
    @WorkerThread
    fun insertAdminToAPI(admin: Admin): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(adminService != null){
                adminService.addAdminAsync(admin)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateAdminInAPI(admin: Admin): Boolean {
        var updated= false
        GlobalScope.launch(Dispatchers.IO) {
            if(adminService != null){
                admin.id?.let { adminService.updateAdminAsync(it,admin) }
                updated = true
            }
        }
        return updated
    }

    @WorkerThread
    fun deleteAdminFromAPI(admin: Admin): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(adminService != null){
                admin.id?.let { adminService.deleteAdminAsync(it) }
                deleted = true
            }
        }
        return deleted
    }

    @WorkerThread
    fun getAdminFromAPI(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {

            if (adminService != null) {
                val response: Response<Admin> = adminService.getAdminByIdAsync(id).await()
                val admin = response.body()

                if (admin != null) {
                    withContext(Dispatchers.IO) {
                        insertAdmin(admin)
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAllAdminsFromAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            if (adminService != null) {
                val response: Response<List<Admin>> = adminService.getAllAdminsAsync()!!.await()
                val admins = response.body()

                if (admins != null) {
                    withContext(Dispatchers.IO) {
                        admins.forEach(this@AdminRepository::cache)
                    }
                }
            }
            else{

            }

        }
    }
}