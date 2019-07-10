package com.schoolfam.parcher.data

import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.data.attendance.Attendance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)

@SmallTest

class AttendanceDaoTest {
    private lateinit var database: ParcherDatabase
    val cal = Calendar.getInstance().time

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        // PowerMockito.mockStatic(Log::class.java)
        database = ParcherDatabase.getInstance(ApplicationProvider.getApplicationContext())/*Room.databaseBuilder(
           ApplicationProvider.getApplicationContext(),
           WorefaDatabase::class.java,"").allowMainThreadQueries().build()*/

    }

    @After
    fun closeDb() = database.close()


    @Test
    fun insertAttendance() = runBlockingTest {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val attencdance = Attendance(3,true,3,cal)
            database.attendanceDao().insertAttendance(attencdance)

            val loaded = database.attendanceDao().getAttendanceById(attencdance.id!!.toLong())

            MatcherAssert.assertThat<Attendance>(loaded as Attendance, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(attencdance.id))

        }
    }

    @Test
    fun deleteAttendance() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val attendance = Attendance(2,true,3,cal)
            database.attendanceDao().insertAttendance(attendance)

            // When deleting a appointment by id
            database.attendanceDao().deleteAttendance(attendance)

            // THEN - The object returned is null
            val getUser = database.attendanceDao().getAttendanceById(attendance.id!!.toLong())
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())
        }
    }
    @Test
    fun updateAttendance() = runBlockingTest {
        // When inserting an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val original = Attendance(4,true,4,cal)
            database.attendanceDao().updateAttendance(original)

            // When an appointment is updated
            val updated = Attendance(4,false,4,cal)
            database.attendanceDao().updateAttendance(updated)

            // THEN - The loaded data contains the expected values
            val loaded = database.attendanceDao().getAttendanceById(original.id!!.toLong())
            MatcherAssert.assertThat(loaded.value?.id, CoreMatchers.`is`("4".toLong()))
            MatcherAssert.assertThat(loaded.value?.date, CoreMatchers.`is`(cal))
            MatcherAssert.assertThat(loaded.value?.status, CoreMatchers.`is`(false))



        }
    }



}

