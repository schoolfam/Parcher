package com.schoolfam.parcher.data

import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.subject.Subject
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

class SubjectDaoTest {
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
    fun insertInsertSubject() = runBlockingTest {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val subj = Subject(3,"Chem")
            database.subjectDao().insertSubject(subj)

            val loaded = database.subjectDao().getSubjectById(subj.id)
            MatcherAssert.assertThat(3, CoreMatchers.`is`(subj.id))


            MatcherAssert.assertThat<Subject>(loaded as Subject, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(subj.id))
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(subj.id))

        }
    }

    @Test
    fun deleteSubject() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val sub = Subject(3,"Bio")
            database.subjectDao().insertSubject(sub)

            // When deleting a appointment by id
            database.subjectDao().deleteSubject(sub)

            // THEN - The object returned is null
            val getUser = database.subjectDao().getSubjectById(sub.id)
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
        }
    }
    @Test
    fun updateSubject() = runBlockingTest {
        // When inserting an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val original = Subject(13,"Chem")
            database.subjectDao().updateSubject(original)

            // When an appointment is updated
            val updated = Subject(3,"HIs")
            database.subjectDao().updateSubject(updated)

            // THEN - The loaded data contains the expected values
            val loaded = database.subjectDao().getSubjectById(original.id)
            MatcherAssert.assertThat(loaded.value?.id, CoreMatchers.`is`("3".toLong()))

        }
    }
    @Test
    fun getAllSubject() = runBlockingTest {
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
    fun delateAllSubject() = runBlockingTest {
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

