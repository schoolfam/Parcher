package com.schoolfam.parcher.repository

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.test.runBlockingTest
import java.time.Instant
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class UserRepositoryTest {
    val today = Date.from(Instant.now())

    private lateinit var repo: UserRepository
    private lateinit var database: ParcherDatabase
    private lateinit var contect: Application
    private lateinit var parcherApiService: ParcherApiService


    @Before
    fun setup() {
        contect = Application()
        parcherApiService = ParcherApiService.getInstance()
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ParcherDatabase::class.java,
            "attendances").allowMainThreadQueries().build()
        repo = UserRepository(database.userDao(),parcherApiService,contect)

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun insertUser()= runBlocking{
        val user = User("Tariku","W/Michae","tar","tar@gmail.com","passs",2,"M")
        repo.insertUser(user)
        //repo.(admin)

        val result  = repo.insertUser(user)

        Assert.assertThat(result, CoreMatchers.notNullValue())

    }


    @Test
    fun deleteUser() = runBlockingTest {

        val user = User("Tariku","W/Michae","tar","tar@gmail.com","passs",2,"M")
        repo.deleteUser(user)


        repo.deleteUser(user)

        val result = repo.allUsers().value
        Assert.assertThat(result, CoreMatchers.nullValue())

    }


}