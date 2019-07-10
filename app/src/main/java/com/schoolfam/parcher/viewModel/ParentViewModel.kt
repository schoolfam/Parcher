package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.network.ParcherApiService
import com.schoolfam.parcher.repository.ParentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParentViewModel(application: Application):AndroidViewModel(application) {

    private val parentRepository: ParentRepository
    val allParents: LiveData<List<Parent>>

    init {
        val parentDao = ParcherDatabase.getInstance(application).parentDao()
        val parcherApiService = ParcherApiService.getInstance()
        parentRepository = ParentRepository(parentDao,parcherApiService,application)
        allParents = parentRepository.allParents()
    }

    fun findParentById(id:Long): LiveData<Parent> {
        return parentRepository.parentsById(id)
    }

    fun insertParent(parent:Parent) =  viewModelScope.launch (Dispatchers.IO){
        parentRepository.insertParent(parent)
    }

    fun updateParent(parent:Parent) =  viewModelScope.launch (Dispatchers.IO){
        parentRepository.updateParent(parent)
    }

    fun deleteParent(parent:Parent) =  viewModelScope.launch (Dispatchers.IO){
        parentRepository.deleteParent(parent)
    }
}