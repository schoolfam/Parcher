package com.schoolfam.parcher.data.role

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RoleDao {

    @Query("SELECT * FROM roles WHERE id = :roleId LIMIT 1")
    fun getRoleById(roleId:Long): LiveData<Role>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRole(role: Role):Long

    @Update
    fun updateRole(role: Role):Int

    @Delete
    fun deleteRole(role: Role):Int

    @Query("SELECT * FROM roles ORDER BY id")
    fun getAllRoles(): LiveData<List<Role>>

}