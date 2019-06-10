package com.schoolfam.parcher.data


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.assessment.Assessment
import com.schoolfam.parcher.data.attendance.Attendance
import com.schoolfam.parcher.data.user.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.time.LocalDateTime
import java.util.*


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest

class AttendanceDaoTest {
    private lateinit var database: ParcherDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ParcherDatabase::class.java,"attendances").allowMainThreadQueries().build()

    }

    @After
    fun closeDb() = database.close()


    @Test
    fun getAttendanceById() = runBlocking {
        // GIVEN - insert a group
        val current = Date.from(Instant.now())
        val attendance = Attendance(4,true,3,current)
        database.assessmentDao().getAssessmentById(attendance.id!!.toLong())

        // WHEN - Get the group by code from the database
        val loaded = database.attendanceDao().getAttendanceById(attendance.id!!.toLong())

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Attendance>(loaded as Attendance, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(attendance.id))
        MatcherAssert.assertThat(loaded.date, CoreMatchers.`is`(attendance.date))
        MatcherAssert.assertThat(loaded.sectionId, CoreMatchers.`is`(attendance.sectionId))
        MatcherAssert.assertThat(loaded.studentId, CoreMatchers.`is`(attendance.studentId))
    }


    @Test
    fun deletAttendance() = runBlockingTest {
        val today = Date.from(Instant.now())
        // Given a group inserted
        val attendance = Attendance(4,false,6,today)
        database.attendanceDao().deleteAttendance(attendance)



        // THEN - The object returned is null
        val getUser = database.attendanceDao().getAttendanceById(attendance.id!!.toLong())
        MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
    }


    @Test
    fun getAttencance() = runBlockingTest {
        val today = Date.from(Instant.now())
        val attendance = Attendance(4,true,2,today)
        database.attendanceDao().getAllAttendances()

        val result = database.adminDao().getAllAdmins()

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }

}


