package com.schoolfam.parcher.data


import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.teacher.Teacher
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
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)

@SmallTest

class TeacherDaoTest {
    private lateinit var database: ParcherDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        // PowerMockito.mockStatic(Log::class.java)
        database = ParcherDatabase.getInstance(ApplicationProvider.getApplicationContext())/*Room.databaseBuilder(
           ApplicationProvider.getApplicationContext(),
           WorefaDatabase::class.java,"WorefaDatabase").allowMainThreadQueries().build()*/

    }

    @After
    fun closeDb() = database.close()


    @Test
    fun insertInsertTeacher() = runBlockingTest {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val teacher = Teacher(3,3)
            database.teacherDao().insertTeacher(teacher)

            val loaded = database.teacherDao().getTeacherById(teacher.userId)
            MatcherAssert.assertThat(3, CoreMatchers.`is`(teacher.userId))


            MatcherAssert.assertThat<Teacher>(loaded as Teacher, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.userId, CoreMatchers.`is`(teacher.userId))
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(teacher.id))

        }
    }

    @Test
    fun deleteTeacher() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val teacher = Teacher(3,2)
            database.teacherDao().insertTeacher(teacher)

            // When deleting a appointment by id
            database.teacherDao().deleteTeacher(teacher)

            // THEN - The object returned is null
            val getUser = database.adminDao().getAdminById(teacher.userId)
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
        }
    }
    @Test
    fun updateTeacher() = runBlockingTest {
        // When inserting an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val original = Admin(13)
            database.adminDao().updateAdmin(original)

            // When an appointment is updated
            val updated = Admin(3)
            database.adminDao().updateAdmin(updated)

            // THEN - The loaded data contains the expected values
            val loaded = database.adminDao().getAdminById(original.userId)
            MatcherAssert.assertThat(loaded.value?.userId, CoreMatchers.`is`("3".toLong()))

        }
    }
    @Test
    fun getAllTeacher() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val admin = Admin(3)
            database.adminDao().insertAdmin(admin)
            val admin2 = Admin(2)
            database.adminDao().insertAdmin(admin2)

            val result = database.adminDao().getAllAdmins()

            MatcherAssert.assertThat(result, CoreMatchers.nullValue())
        }
    }
    @Test
    fun delateAllTeacher() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val admin = Admin(3)
            database.adminDao().insertAdmin(admin)
            val admin2 = Admin(2)
            database.adminDao().insertAdmin(admin2)

            database.adminDao().deleteAllAdmins()

            val getUser = database.adminDao().getAdminById(admin.userId)
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())

        }
    }


}

