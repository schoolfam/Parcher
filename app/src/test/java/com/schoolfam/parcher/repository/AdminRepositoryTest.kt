package com.schoolfam.parcher.repository
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.ParcherDatabase
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.parent.Parent
import com.schoolfam.parcher.network.ParcherApiService


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest

class AdminRepositoryTest {

    private lateinit var repo: AdminRepository
    private lateinit var database: ParcherDatabase
    private lateinit var parcherApiService: ParcherApiService



    @Before
    fun setup() {
        parcherApiService = ParcherApiService.getInstance()
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ParcherDatabase::class.java,
            "admins").allowMainThreadQueries().build()
        repo = AdminRepository(database.adminDao(),parcherApiService)

    }

    @After
    fun cleanUp() {
        database.close()
    }
    @Test
    fun deleteParent() = runBlockingTest {

        val parent = Admin(2)
        repo.deleteAdmin(parent)


        repo.deleteAdmin(parent)

        val result = repo.allAdmins().value
        Assert.assertThat(result, CoreMatchers.nullValue())

    }

      @Test
      fun insertAdmin() = runBlockingTest {

          val admin = Admin(2)
          repo.insertAdmin(admin)


          repo.allAdmins()

          val result = repo.allAdmins()
          Assert.assertThat(result, CoreMatchers.nullValue())

      }


}