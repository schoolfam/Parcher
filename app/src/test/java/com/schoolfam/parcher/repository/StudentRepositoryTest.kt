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
import com.schoolfam.parcher.data.student.Student
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.test.runBlockingTest
import java.time.Instant
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class StudentRepositoryTest {
    val today = Date.from(Instant.now())

    private lateinit var repo: StudentRepository
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
        repo = StudentRepository(database.studentDao(),parcherApiService)

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun deleteStudent() = runBlockingTest {

        val student = Student(2,4,2)
        repo.deleteStudent(student)


        repo.deleteStudent(student)

        val result = repo.allStudent().value
        Assert.assertThat(result, CoreMatchers.nullValue())

    }
    @Test
    fun insertStudent()= runBlocking{
        val student = Student(2,3,4)
        repo.insertStudent(student)
        //repo.(admin)

        val result  = repo.insertStudent(student)

        Assert.assertThat(result, CoreMatchers.notNullValue())

    }





}