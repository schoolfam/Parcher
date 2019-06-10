package com.schoolfam.parcher.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.parent.ParentDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ParentRepository(private val parentDao: ParentDao, private val parentService: ParcherApiService?) {

    private fun cache(parent:Parent){
        parentDao.insertParent(parent)
    }

    fun insertParent(parent: Parent){
        if (insertParentToAPI(parent)) {
            parentDao.insertParent(parent)
        }
    }
    fun updateParent(parent: Parent){
        if (updateParentInAPI(parent)) {
            parentDao.updateParent(parent)
        }
    }
    fun deleteParent(parent: Parent){
        if (deleteParentFromAPI(parent)) {
            parentDao.deleteParent(parent)
        }
    }

    fun allParents(): LiveData<List<Parent>> =  parentDao.getAllParents()

    fun parentsById(id:Long): LiveData<Parent> = parentDao.getParentById(id)

    //network functions
    @WorkerThread
    fun insertParentToAPI(parent: Parent): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(parentService != null){
                parentService.addParentAsync(parent)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateParentInAPI(parent: Parent): Boolean {
        var updated= false
        GlobalScope.launch(Dispatchers.IO) {
            if(parentService != null){
                parent.id?.let { parentService.updateParentAsync(it,parent) }
                updated = true
            }
        }
        return updated
    }

    @WorkerThread
    fun deleteParentFromAPI(parent: Parent): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(parentService != null){
                parent.id?.let { parentService.deleteParentAsync(it) }
                deleted = true
            }
        }
        return deleted
    }

    @WorkerThread
    fun getParentFromAPI(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {

            if (parentService != null) {
                val response: Response<Parent> = parentService.getParentByIdAsync(id).await()
                val parent = response.body()

                if (parent != null) {
                    withContext(Dispatchers.IO) {
                        insertParent(parent)
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAllParentsFromAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            if (parentService != null) {
                val response: Response<List<Parent>> = parentService.getAllParentsAsync()!!.await()
                val parents = response.body()

                if (parents != null) {
                    withContext(Dispatchers.IO) {
                        parents.forEach(this@ParentRepository::cache)
                    }
                }
            }
            else{

            }
        }
    }
}