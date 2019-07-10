package com.schoolfam.parcher.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.data.user.UserDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.*
import retrofit2.Response

class UserRepository(private val userDao: UserDao, private val parcherApiService: ParcherApiService,private val context: Context) {

    val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

    fun allUsers(): LiveData<List<User>> {
        GlobalScope.launch { refreshDb() }
        return userDao.getAllUsers()
    }
    fun userById(id:Long):LiveData<User> {
        return userDao.getUserById(id)
    }
    fun userByEmail(email:String):LiveData<User> {
        return userDao.getUserByEmail(email)
    }

    fun insertUser(user: User):Long{
            val userId = addUserAsync(user)
            GlobalScope.launch { refreshDb()}
            return userId


    }
    fun updateUser(user: User):Long{
        var success = false
        if(networkInfo !=null && networkInfo.isConnected){
             success = updateUserAsync(user)
        }

        GlobalScope.launch { refreshDb() }
        if(success){
            return 0L
        }
        return -1L
    }
    fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    fun deleteAllUsers(){
      userDao.deleteAllUser()
    }

    suspend fun refreshDb(){
        withContext(Dispatchers.IO) {

            if(networkInfo !=null && networkInfo.isConnected){
                val posts = getAllUsersAsync().value
                if(posts != null) {
                    deleteAllUsers()
                    userDao.insertAllUsers(posts)
                }
            }
        }
    }

    private fun getAllUsersAsync():LiveData<List<User>>{
        var usersFromApi:List<User>? = null
        val users:MutableList<User> = emptyList<User>().toMutableList()
        runBlocking(Dispatchers.IO) {
            val response: Response<List<User>> = parcherApiService.getAllUsersAsync().await()
            if(response.isSuccessful){
                usersFromApi = response.body()
               // Log.d("I got no post", " still none-----------------------------------------------------------------------------------------------------------------------------------------"+usersFromApi!!.size)


                if(usersFromApi != null){
                    for(user:User in usersFromApi as List<User>){
                        users.add(user)
                        //Log.d("I got no post", " still none Mute-----------------------------------------------------------------------------------------------------------------------------------------"+users.size)

                    }
                }
                else
                {
                    Log.d("I got no post", " still none")
                }
            }
            else
            {
                Log.d("Get all post: ", response.message())
            }
        }
        return MutableLiveData<List<User>>(users)

    }

    private fun addUserAsync(user: User):Long{

        var cUser:User? = null

        runBlocking(Dispatchers.IO) {
            val response:Response<User> = parcherApiService.addUserAsync(user).await()
            if(response.isSuccessful){
                cUser = response.body() }
            else Log.d("Add post ASync", response.code().toString())
        }
        return cUser!!.id!!
    }
    private fun updateUserAsync(user: User):Boolean{

        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response:Response<Void> = parcherApiService.updateUserAsync(user).await()
            if(response.isSuccessful){
                 isSuccessful = true
            }
            else Log.d("Add post ASync", response.code().toString())
        }
        return isSuccessful
    }





}