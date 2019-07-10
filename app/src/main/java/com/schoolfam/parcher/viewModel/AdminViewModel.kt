package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.network.ParcherApiService
import com.schoolfam.parcher.repository.AdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminViewModel(application: Application): AndroidViewModel(application) {

    private val adminRepository: AdminRepository
    val allAdmins: LiveData<List<Admin>>

    init {
        val adminDao = ParcherDatabase.getInstance(application).adminDao()
        val parcherApiService = ParcherApiService.getInstance()
        adminRepository = AdminRepository(adminDao,parcherApiService,application)
        allAdmins = adminRepository.allAdmins()
    }

    fun findAdminById(id:Long): LiveData<Admin> {
        return adminRepository.adminById(id)
    }


    fun insertAdmin(admin:Admin) =  viewModelScope.launch (Dispatchers.IO){
        adminRepository.insertAdmin(admin)
    }

    fun updateAdmin(admin:Admin) =  viewModelScope.launch (Dispatchers.IO){
        adminRepository.updateAdmin(admin)
    }

    fun deleteAdmin(admin:Admin) =  viewModelScope.launch (Dispatchers.IO){
        adminRepository.deleteAdmin(admin)
    }

}