package com.schoolfam.parcher.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.data.user.UserDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val userDao: UserDao, private val userService: ParcherApiService?) {

    private fun cache(user: User){
        userDao.insertUser(user)
    }

    fun insertUser(user: User):Long{
        if (insertUserToAPI(user)){
            userDao.insertUser(user)
        }
        return userDao.insertUser(user)
    }

    fun updateUser(user: User){
        if (updateUserInAPI(user)) {
            userDao.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        if (deleteUserFromAPI(user)) {
            userDao.deleteUser(user)
        }
    }

    fun allUsers(): LiveData<List<User>> =  userDao.getAllUsers()

    fun userById(id:Long):LiveData<User> = userDao.getUserById(id)

    fun userByEmail(email:String):LiveData<User> = userDao.getUserByEmail(email)


    //network functions
    @WorkerThread
    fun insertUserToAPI(user: User): Boolean {
        var added= false
        GlobalScope.launch(Dispatchers.IO) {
            if(userService != null){
                userService.addUserAsync(user)
                added = true
            }
        }
        return added
    }

    @WorkerThread
    fun updateUserInAPI(user: User): Boolean {
        var updated= false
        GlobalScope.launch(Dispatchers.IO) {
            if(userService != null){
                user.id?.let { userService.updateUserAsync(it,user) }
                updated = true
            }
        }
        return updated
    }

    @WorkerThread
    fun deleteUserFromAPI(user: User): Boolean {
        var deleted= false
        GlobalScope.launch(Dispatchers.IO) {
            if(userService != null){
                user.id?.let { userService.deleteUserAsync(it) }
                deleted = true
            }
        }
        return deleted
    }

    @WorkerThread
    fun getUserFromAPI(id: Long) {
        GlobalScope.launch(Dispatchers.IO) {

            if (userService != null) {
                val response: Response<User> = userService.getUserByIdAsync(id).await()
                val user = response.body()

                if (user != null) {
                    withContext(Dispatchers.IO) {
                        insertUser(user)
                    }
                }
            }
        }
    }

    @WorkerThread
    fun getAllUsersFromAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            if (userService != null) {
                val response: Response<List<User>> = userService.getAllUsersAsync()!!.await()
                val users = response.body()

                if (users != null) {
                    withContext(Dispatchers.IO) {
                        users.forEach(this@UserRepository::cache)
                    }
                }
            }
            else{

            }

        }
    }
}