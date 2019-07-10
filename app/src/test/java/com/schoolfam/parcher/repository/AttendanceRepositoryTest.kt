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
import com.schoolfam.parcher.network.ParcherApiService
import kotlinx.coroutines.test.runBlockingTest
import java.time.Instant
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class AttendanceRepositoryTest {
    val today = Date.from(Instant.now())

    private lateinit var repo: AttendanceRepository
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
        repo = AttendanceRepository(database.attendanceDao(),parcherApiService)

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun insertAttendance()= runBlocking{
        val attendance = Attendance(2,true,1,today)
        repo.insertAttendance(attendance)
        //repo.(admin)

        val result  = repo.insertAttendance(attendance)

        Assert.assertThat(result, CoreMatchers.notNullValue())

    }


    @Test
    fun deleteAttendance() = runBlockingTest {

        val attendance = Attendance(2,true,2,today)
        repo.deleteAttendance(attendance)


        repo.deleteAttendance(attendance)

        val result = repo.allAttendance().value
        Assert.assertThat(result, CoreMatchers.nullValue())

    }


}