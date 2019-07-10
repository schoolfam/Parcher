package com.schoolfam.parcher.repository


import android.app.Application
import com.schoolfam.parcher.data.parent.Parent



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
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.network.ParcherApiService
import java.time.Instant
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class ParentRepositoryTest {
    val today = Date.from(Instant.now())

    private lateinit var repo: ParentRepository
    private lateinit var database: ParcherDatabase
    private lateinit var contect: Application
    private lateinit var parcherApiService: ParcherApiService



    @Before
    fun setup() {
        parcherApiService = ParcherApiService.getInstance()
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ParcherDatabase::class.java,
            "parents").allowMainThreadQueries().build()
        repo = ParentRepository(database.parentDao(),parcherApiService)

    }

    @After
    fun cleanUp() {
        database.close()
    }


    @Test
    fun deleteParent() = runBlockingTest {

        val parent = Parent(2,2345678)
        repo.deleteParent(parent)


        repo.deleteParent(parent)

        val result = repo.allParents().value
        Assert.assertThat(result, CoreMatchers.nullValue())

    }
    @Test
    fun insertParent()= runBlocking{
        val parent = Parent(2,2345678)
        repo.insertParent(parent)
        //repo.(admin)

        val result  = repo.insertParent(parent)

        Assert.assertThat(result, CoreMatchers.notNullValue())

    }

}