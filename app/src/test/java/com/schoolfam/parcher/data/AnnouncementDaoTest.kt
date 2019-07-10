package com.schoolfam.parcher.data


import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.announcement.Announcement
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

class AnnouncementDaoTest {
    private lateinit var database: ParcherDatabase
    val cal = Calendar.getInstance().time

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
    fun insertAnnouncement() = runBlockingTest {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val announcement = Announcement("Registration","next weak",cal)
            database.announcementDao().insertAnnouncement(announcement)

            val loaded = database.announcementDao().getAnnouncementById(announcement.id!!.toLong())

            MatcherAssert.assertThat<Announcement>(loaded as Announcement, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(announcement.id))

        }
    }

    @Test
    fun deleteAnnouncement() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val announcement = Announcement("do","do",cal)
            database.announcementDao().insertAnnouncement(announcement)

            // When deleting a appointment by id
            database.announcementDao().deleteAnnouncement(announcement)

            // THEN - The object returned is null
            val getUser = database.announcementDao().getAnnouncementById(announcement.id!!.toLong())
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())
        }
    }
    @Test
    fun updateAnnouncement() = runBlockingTest {
        // When inserting an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val original = Announcement("gog","gogo",cal)
            database.announcementDao().updateAnnouncement(original)

            // When an appointment is updated
            val updated = Announcement("dod","dod",cal)
            database.announcementDao().updateAnnouncement(updated)

            // THEN - The loaded data contains the expected values
            val loaded = database.announcementDao().getAnnouncementById(original.id!!.toLong())
            MatcherAssert.assertThat(loaded.value?.id, CoreMatchers.`is`("3".toLong()))
            MatcherAssert.assertThat(loaded.value?.date, CoreMatchers.`is`(cal))
            MatcherAssert.assertThat(loaded.value?.title, CoreMatchers.`is`("dod"))



        }
    }
    @Test
    fun getAllAnnouncement() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val announcement = Announcement("go","gog",cal)
            database.announcementDao().insertAnnouncement(announcement)
            val announcement2 = Announcement("pip","pip",cal)
            database.announcementDao().insertAnnouncement(announcement2)

            val result = database.announcementDao().getAllAnnouncement()

            MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        }
    }
    @Test
    fun delateAllAnnounce() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val announcement = Announcement("pip","done man",cal)
            database.announcementDao().insertAnnouncement(announcement)
            val announcement2 = Admin(2)
            database.adminDao().insertAdmin(announcement2)

            database.adminDao().deleteAllAdmins()

            val getUser = database.adminDao().getAdminById(announcement.id!!.toLong())
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())

        }
    }


}

