package com.schoolfam.parcher.data.user

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM subjects WHERE id = :userId LIMIT 1")
    fun getUserById(userId:String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User):Int

    @Delete
    fun deleteUser(user: User):Int

    @Query("SELECT * FROM users ORDER BY id")
    fun getAllUsers(user: User): LiveData<List<User>>

}