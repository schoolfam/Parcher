package com.schoolfam.parcher.repository

import com.schoolfam.parcher.data.teacher.Teacher




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
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.test.runBlockingTest
import java.time.Instant
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class TeacherRepositoryTest {
    val today = Date.from(Instant.now())

    private lateinit var repo: TeacherRepository
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
        repo = TeacherRepository(database.teacherDao(),parcherApiService)

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun deleteTeacher() = runBlockingTest {

        val teacher = Teacher(2,2)
        repo.deleteTeacher(teacher)


        repo.deleteTeacher(teacher)

        val result = repo.allTeachers().value
        Assert.assertThat(result, CoreMatchers.nullValue())

    }
    @Test
    fun insertTeacher()= runBlocking{
        val teacher = Teacher(2,2)
        repo.insertTeacher(teacher)
        //repo.(admin)

        val result  = repo.insertTeacher(teacher)

        Assert.assertThat(result, CoreMatchers.notNullValue())

    }





}