package com.schoolfam.parcher.data.parent

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ParentDao {

    @Query("SELECT * FROM parents WHERE id = :parentId LIMIT 1")
    fun getParentById(parentId:String): LiveData<Parent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParent(parent: Parent):Long

    @Update
    fun updateParent(parent: Parent):Int

    @Delete
    fun deleteParent(parent: Parent):Int

    @Query("SELECT * FROM parents ORDER BY id")
    fun getAllParents(parent: Parent): LiveData<List<Parent>>

}