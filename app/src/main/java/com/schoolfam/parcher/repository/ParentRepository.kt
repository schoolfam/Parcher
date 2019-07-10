package com.schoolfam.parcher.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.parent.ParentDao
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.*
import retrofit2.Response

class ParentRepository(private val parentDao: ParentDao,private val parcherApiService: ParcherApiService,private val context: Context) {

    val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo


    fun allParents(): LiveData<List<Parent>> {
        GlobalScope.launch { refreshDb() }
        return parentDao.getAllParents()
    }
    fun parentsById(id:Long): LiveData<Parent> {

        return parentDao.getParentById(id)
    }

    fun insertParent(parent: Parent):Long{
        val success = addParentAsync(parent)
        GlobalScope.launch { refreshDb() }
//        Log.d("I got no post", " still none Inserted-----------------------------------------------------------------------------------------------------------------------------------------"+success)

        if(success){
            return 0L
        }
        return -1L
    }
    fun updateParent(parent: Parent){

        parentDao.updateParent(parent)
    }
    fun deleteParent(parent: Parent){

        parentDao.deleteParent(parent)
    }

    fun deleteAllParent(){
        parentDao.deleteAllParents()
    }

    suspend fun refreshDb(){
        withContext(Dispatchers.IO) {
                        if(networkInfo !=null && networkInfo.isConnected){
                val posts = getAllParentAsync().value
                if(posts != null) {
                    deleteAllParent()
                    parentDao.insertAllParents(posts)
                }
            }

        }
    }

    private fun getAllParentAsync():LiveData<List<Parent>>{
        var parentFromApi:List<Parent>? = null
        val parents:MutableList<Parent> = emptyList<Parent>().toMutableList()
        runBlocking(Dispatchers.IO) {
            val response: Response<List<Parent>> = parcherApiService.getAllParentsAsync().await()
            if(response.isSuccessful){
                parentFromApi = response.body()

                if(parentFromApi != null){
                    for(parent:Parent in parentFromApi as List<Parent>){
                        parents.add(parent)
                    }
                }
                else
                {
                    Log.d("I got no parent", " still none")
                }
            }
            else
            {
                Log.d("Get all parent: ", response.message())
            }
        }
        return MutableLiveData<List<Parent>>(parents)
    }

    private fun addParentAsync(parent: Parent):Boolean{
        var isSuccessful = false

        runBlocking(Dispatchers.IO) {
            val response: Response<Void> = parcherApiService.addParentAsync(parent).await()
            if(response.isSuccessful){
                isSuccessful = true}
            else Log.d("Add Parent ASync", response.code().toString())
        }
        return isSuccessful
    }
}