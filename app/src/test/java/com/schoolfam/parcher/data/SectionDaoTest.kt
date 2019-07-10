package com.schoolfam.parcher.data

import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.data.section.Section
import com.schoolfam.parcher.data.section.SectionDao
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

class SectionDaoTest {
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
    fun insertInsertSection() = runBlockingTest {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val sect = Section(3,"B")
            database.sectionDao().insertSection(sect)

            val loaded = database.sectionDao().getSectionById(sect.id)
            MatcherAssert.assertThat(3, CoreMatchers.`is`(sect.id))


            MatcherAssert.assertThat<Section>(loaded as Section, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(sect.id))
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(sect.id))

        }
    }

    @Test
    fun deleteSection() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val sec = Section(3,"A")
            database.sectionDao().insertSection(sec)

            // When deleting a appointment by id
            database.sectionDao().deleteSection(sec)

            // THEN - The object returned is null
            val getUser = database.sectionDao().getSectionById(sec.id)
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
        }
    }
    @Test
    fun updateSection() = runBlockingTest {
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
    fun getAllSection() = runBlockingTest {
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
    fun delateSection() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val sec = Section(3,"C")
            database.sectionDao().insertSection(sec)
            val sec2 = Section(2,"M")
            database.sectionDao().insertSection(sec2)


            val getUser = database.adminDao().getAdminById(sec2.id)
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())

        }
    }


}

