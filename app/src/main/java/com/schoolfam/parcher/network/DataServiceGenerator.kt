package com.schoolfam.parcher.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.schoolfam.parcher.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DataServiceGenerator {

    fun createParcherApiService(context: Context): ParcherApiService? {
        val connected = checkInternet(context)

        if(connected != null && connected ) {
            val builder = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create()) // GsonConverterFactory
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("http://192.168.1.4:8080/")
            val httpClient = OkHttpClient.Builder()
                .cache(null)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(interceptor)
            }
            builder.client(httpClient.build())
            val retrofit = builder.build()
            return retrofit.create(ParcherApiService::class.java)
        }
        return null

    }
    companion object{
        fun checkInternet(context: Context): Boolean? {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return activeNetwork?.isConnected
        }
    }

}