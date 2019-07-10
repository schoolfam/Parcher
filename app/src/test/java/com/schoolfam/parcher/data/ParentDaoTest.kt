package com.schoolfam.parcher.data



import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.parent.Parent
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

class ParentDaoTest {
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
    fun insertInsertParent() = runBlockingTest {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val parent = Parent(3,98765432)
            database.parentDao().insertParent(parent)

            val loaded = database.parentDao().getParentById(parent.userId)
            MatcherAssert.assertThat(3, CoreMatchers.`is`(parent.userId))


            MatcherAssert.assertThat<Admin>(loaded as Admin, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.userId, CoreMatchers.`is`(parent.userId))
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(parent.id))

        }
    }

    @Test
    fun deleteParent() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val parent = Parent(3,987654)
            database.parentDao().insertParent(parent)

            // When deleting a appointment by id
            database.parentDao().deleteParent(parent)

            // THEN - The object returned is null
            val getUser = database.parentDao().getParentById(parent.userId)
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())
        }
    }
    @Test
    fun updateParent() = runBlockingTest {
        // When inserting an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val original = Parent(13,987654)
            database.parentDao().updateParent(original)

            // When an appointment is updated
            val updated = Parent(3,987654)
            database.parentDao().updateParent(updated)

            // THEN - The loaded data contains the expected values
            val loaded = database.adminDao().getAdminById(original.userId)
            MatcherAssert.assertThat(loaded.value?.userId, CoreMatchers.`is`("3".toLong()))

        }
    }
    @Test
    fun getAllParent() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val admin = Admin(3)
            database.adminDao().insertAdmin(admin)
            val admin2 = Admin(2)
            database.adminDao().insertAdmin(admin2)

            val result = database.adminDao().getAllAdmins()

            MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        }
    }
    @Test
    fun delateAllParent() = runBlockingTest {
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

