package com.schoolfam.parcher.repository

import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.data.user.UserDao

class UserRepository(private val userDao: UserDao) {

    fun allUsers(): LiveData<List<User>> =  userDao.getAllUsers()
    fun userById(id:Long):LiveData<User> = userDao.getUserById(id)
    fun userByEmail(email:String):LiveData<User> = userDao.getUserByEmail(email)

    fun insertUser(user: User){
        userDao.insertUser(user)
    }
    fun updateUser(user: User){
        userDao.updateUser(user)
    }
    fun deleteUser(user: User){
        userDao.deleteUser(user)
    }



}