package com.schoolfam.parcher.data.user

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.FtsOptions.Order
import androidx.room.OnConflictStrategy



@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    fun getUserById(userId:Long): LiveData<User>

    @Query("SELECT * FROM users WHERE emailAddress = :email LIMIT 1")
    fun getUserByEmail(email:String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User):Int

    @Delete
    fun deleteUser(user: User):Int

    @Query("SELECT * FROM users ORDER BY id")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUsers(users: List<User>)

    @Query("DELETE FROM users")
    fun deleteAllUser()

}