package com.schoolfam.parcher.data



import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.student.Student
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

class StudentDaoTest {
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
    fun insertStudent() = runBlockingTest {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val stu = Student(3,3,2)
            database.studentDao().insertStudent(stu)

            val loaded = database.studentDao().getStudentById(stu.userId)
            MatcherAssert.assertThat(3, CoreMatchers.`is`(stu.userId))


            MatcherAssert.assertThat<Student>(loaded as Student, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.userId, CoreMatchers.`is`(stu.userId))
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(stu.id))

        }
    }

    @Test
    fun deleteStudent() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val stu = Student(3,3,5)
            database.studentDao().insertStudent(stu)

            // When deleting a appointment by id
            database.studentDao().deleteStudent(stu)

            // THEN - The object returned is null
            val getUser = database.studentDao().getStudentById(stu.userId)
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
        }
    }
    @Test
    fun updateStudent() = runBlockingTest {
        // When inserting an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val original = Student(13,4,5)
            database.studentDao().updateStudent(original)

            // When an appointment is updated
            val updated = Student(3,4,5)
            database.studentDao().updateStudent(updated)

            // THEN - The loaded data contains the expected values
            val loaded = database.studentDao().getStudentById(original.userId)
            MatcherAssert.assertThat(loaded.value?.userId, CoreMatchers.`is`("3".toLong()))

        }
    }
    @Test
    fun getAllStudent() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val stu = Student(3,35,5)
            database.studentDao().insertStudent(stu)
            val stu2 = Student(2,34,1)
            database.studentDao().insertStudent(stu2)

            val result = database.studentDao().getAllStudents()

            MatcherAssert.assertThat(result, CoreMatchers.nullValue())
        }
    }
    @Test
    fun delateAllStudents() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val stu = Student(3,4,6)
            database.studentDao().insertStudent(stu)
            val stu2 = Student(2,3,5)
            database.studentDao().insertStudent(stu2)


            val getUser = database.adminDao().getAdminById(stu2.userId)
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())

        }
    }


}

