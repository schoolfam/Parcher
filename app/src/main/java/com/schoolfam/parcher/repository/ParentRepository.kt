package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.parent.ParentDao

class ParentRepository(private val parentDao: ParentDao) {
    fun allParents(): LiveData<List<Parent>> =  parentDao.getAllParents()
    fun parentsById(id:Long): LiveData<Parent> = parentDao.getParentById(id)

    fun insertParent(parent: Parent){
        parentDao.insertParent(parent)
    }
    fun updateParent(parent: Parent){
        parentDao.updateParent(parent)
    }
    fun deleteParent(parent: Parent){
        parentDao.deleteParent(parent)
    }
}