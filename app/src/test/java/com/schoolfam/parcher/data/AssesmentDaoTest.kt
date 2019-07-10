package com.schoolfam.parcher.data

import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.announcement.Announcement
import com.schoolfam.parcher.data.assessment.Assessment
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

class AssesmentDaoTest {
    private lateinit var database: ParcherDatabase
    @Before
    fun initDb() {

        database = ParcherDatabase.getInstance(ApplicationProvider.getApplicationContext())
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun deleteAssesment() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val assessment = Assessment(1,1,1,1.1)
            database.assessmentDao().insertAssessment(assessment)

            // When deleting a appointment by id
            database.assessmentDao().deleteAssessment(assessment)

            // THEN - The object returned is null
            val getUser = database.assessmentDao().getAssessmentById(assessment.id!!.toLong())
            MatcherAssert.assertThat(getUser, CoreMatchers.nullValue())
        }
    }
    //INSERT ASSESSMENT
    @Test
    fun insertAssesment() = runBlockingTest {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val assessement = Assessment(3,3,3,2.1)
            database.assessmentDao().insertAssessment(assessement)

            val loaded = database.assessmentDao().getAssessmentById(assessement.id!!.toLong())

            MatcherAssert.assertThat<Assessment>(loaded as Assessment, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(assessement.id))

        }
    }


    @Test
    fun updateAssessment() = runBlockingTest {
        // When inserting an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val original = Assessment(3,3,3,3.1)
            database.assessmentDao().updateAssessment(original)

            // When an appointment is updated
            val updated = Assessment(2,2,2,4.1)
            database.assessmentDao().updateAssessment(updated)

            // THEN - The loaded data contains the expected values
            val loaded = database.assessmentDao().getAssessmentById(original.id!!.toLong())
            MatcherAssert.assertThat(loaded.value?.id, CoreMatchers.`is`(updated.id))
            MatcherAssert.assertThat(loaded.value?.studentId, CoreMatchers.`is`(updated.studentId))
            MatcherAssert.assertThat(loaded.value?.subjectId, CoreMatchers.`is`(updated.studentId))



        }
    }
    @Test
    fun getAllAssessment() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val assessment = Assessment(2,2,2,2.1)
            database.assessmentDao().insertAssessment(assessment)
            val assessment2 = Assessment(5,5,5,5.1)
            database.assessmentDao().insertAssessment(assessment)
            val result = database.assessmentDao().getAllAssessments()
            MatcherAssert.assertThat(result, CoreMatchers.nullValue())
        }
    }
    @Test
    fun delateAllAssesement() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val assessment = Assessment(6,6,6,6.1)
            database.assessmentDao().insertAssessment(assessment)
            val assessment2 = Assessment(2,4,5,7.1)
            database.assessmentDao().insertAssessment(assessment2)

            database.assessmentDao().deleteAssessment(assessment)

            val getUser = database.assessmentDao().getAssessmentById(assessment.id!!.toLong())
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())

        }
    }


}

