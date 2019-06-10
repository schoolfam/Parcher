package com.schoolfam.parcher.data


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.assessment.Assessment
import com.schoolfam.parcher.data.user.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest

class AssessmentDaoTest {
    private lateinit var database: ParcherDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ParcherDatabase::class.java,"assessments").allowMainThreadQueries().build()

    }

    @After
    fun closeDb() = database.close()


    @Test
    fun getAssessmentById() = runBlocking {
        // GIVEN - insert a group
        val assessment = Assessment(4,3,2,5.3)
        database.assessmentDao().getAssessmentById(assessment.id!!.toLong())

        // WHEN - Get the group by code from the database
        val loaded = database.assessmentDao().getAssessmentById(assessment.id!!.toLong())

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<Assessment>(loaded as Assessment, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(assessment.id))
         MatcherAssert.assertThat(loaded.score, CoreMatchers.`is`(assessment.score))
         MatcherAssert.assertThat(loaded.subjectId, CoreMatchers.`is`(assessment.subjectId))
         MatcherAssert.assertThat(loaded.studentId, CoreMatchers.`is`(assessment.studentId))
    }


    @Test
    fun deleteAssessmentById() = runBlocking {
        // Given a group inserted
        val assessment = Assessment(4,3,6,1.2)
        database.assessmentDao().deleteAssessment(assessment)



        // THEN - The object returned is null
        val getUser = database.assessmentDao().getAssessmentById(assessment.id!!.toLong())
        MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
    }


    @Test
    fun getAllUsers() = runBlocking {
        val admin = Admin(4)
        database.adminDao().insertAdmin(admin)

        val result = database.adminDao().getAllAdmins()

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }

}


