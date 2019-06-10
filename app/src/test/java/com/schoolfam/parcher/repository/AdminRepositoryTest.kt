package com.schoolfam.parcher.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.admin.Admin


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class AdminRepositoryTest {

    private lateinit var repo: AdminRepository
    private lateinit var database: ParcherDatabase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ParcherDatabase::class.java,
            "admins").allowMainThreadQueries().build()
        repo = AdminRepository(database.adminDao())

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun insertAndRetrieve()= runBlocking{
        val admin = Admin(2)
        repo.adminById(admin.id!!.toLong())
        //repo.(admin)

        val result  = repo.adminById(admin.id!!.toLong())

        Assert.assertThat(result, CoreMatchers.notNullValue())



    }

    @Test
    fun deleteAppointmentRetrieveNull() = runBlockingTest {

        val admin = Admin(2)
        repo.insertAdmin(admin)


        repo.allAdmins()

        val result = repo.allAdmins()
        Assert.assertThat(result, CoreMatchers.nullValue())

    }


}