package com.schoolfam.parcher.data.admin

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AdminDao {

    @Query("SELECT * FROM admins WHERE id = :adminId LIMIT 1")
    fun getAdminById(adminId:Long): LiveData<Admin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAdmin(admin: Admin):Long

    @Update
    fun updateAdmin(admin: Admin):Int

    @Delete
    fun deleteAdmin(admin: Admin):Int

    @Query("SELECT * FROM admins ORDER BY id")
    fun getAllAdmins(): LiveData<List<Admin>>

}