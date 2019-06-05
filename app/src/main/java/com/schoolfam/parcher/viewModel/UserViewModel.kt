package com.schoolfam.parcher.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application:Application):AndroidViewModel(application) {

    private val userRepository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = ParcherDatabase.getInstance(application).userDao()
        userRepository = UserRepository(userDao)
        allUsers = userRepository.allUsers()
    }

    fun findUserById(id:Long):LiveData<User>{
        return userRepository.userById(id)
    }
    fun findUserByEmail(email:String):LiveData<User>{
        return userRepository.userByEmail(email)
    }

    fun insertUser(user:User) =  viewModelScope.launch (Dispatchers.IO){
        userRepository.insertUser(user)
    }

    fun updateUser(user:User) =  viewModelScope.launch (Dispatchers.IO){
        userRepository.updateUser(user)
    }

    fun deleteUser(user:User) =  viewModelScope.launch (Dispatchers.IO){
        userRepository.deleteUser(user)
    }

}