package com.schoolfam.parcher.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.user.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.core.app.ApplicationProvider.getApplicationContext



@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest

class AdminDaoTest {
        private lateinit var database: ParcherDatabase

        @Before
        fun initDb() {
            // using an in-memory database because the information stored here disappears when the
            // process is killed
            database = Room.databaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ParcherDatabase::class.java,"WorefaDatabase").allowMainThreadQueries().build()

        }

        @After
        fun closeDb() = database.close()


        @Test
        fun insertGroupAndGetByEmail() = runBlocking {
            // GIVEN - insert a group
            val admin = Admin(4)
            database.adminDao().insertAdmin(admin)

            // WHEN - Get the group by code from the database
            val loaded = database.adminDao().getAdminById(admin.id!!.toLong())

            // THEN - The loaded data contains the expected values
            MatcherAssert.assertThat<Admin>(loaded as Admin, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(admin.id))
           /* MatcherAssert.assertThat(loaded.fname, CoreMatchers.`is`(user.fname))
            MatcherAssert.assertThat(loaded.lname, CoreMatchers.`is`(user.lname))
            MatcherAssert.assertThat(loaded.mname, CoreMatchers.`is`(user.mname))*/
        }


        @Test
        fun deleteUserAndGet(){
            // Given a group inserted
            val admin = Admin(4)
            database.adminDao().deleteAdmin(admin)



            // THEN - The object returned is null
            val getUser = database.adminDao().getAdminById(admin.id!!.toLong())
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
        }


        @Test
        fun getAllUsers() {
            val admin = Admin(4)
            database.adminDao().insertAdmin(admin)

            val result = database.adminDao().getAllAdmins()

            MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        }

    }


