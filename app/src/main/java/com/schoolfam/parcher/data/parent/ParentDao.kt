package com.schoolfam.parcher.data.parent

import androidx.lifecycle.LiveData
import androidx.room.*
import com.schoolfam.parcher.data.user.User

@Dao
interface ParentDao {

    @Query("SELECT * FROM parents WHERE id = :parentId LIMIT 1")
    fun getParentById(parentId:Long): LiveData<Parent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParent(parent: Parent):Long

    @Update
    fun updateParent(parent: Parent):Int

    @Delete
    fun deleteParent(parent: Parent):Int

    @Query("SELECT * FROM parents ORDER BY id")
    fun getAllParents(): LiveData<List<Parent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllParents(parents: List<Parent>)

    @Query("DELETE FROM parents")
    fun deleteAllParents()

}