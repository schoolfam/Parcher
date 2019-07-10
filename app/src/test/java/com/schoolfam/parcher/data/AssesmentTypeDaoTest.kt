package com.schoolfam.parcher.data

import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.data.admin.Admin
import com.schoolfam.parcher.data.assessmentType.AssessmentType
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

class AssesmentTypeDaoTest {
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
    fun insertAsseType()  {
        // GIVEN - insert an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val asstype = AssessmentType(3,"Chemestry",90.2)
            database.assessmentTypeDao().insertAssessmentType(asstype)

            val loaded = database.assessmentTypeDao().getAssessmentTypeById(asstype.id)
            MatcherAssert.assertThat(3, CoreMatchers.`is`(asstype.id))


            MatcherAssert.assertThat<AssessmentType>(loaded as AssessmentType, CoreMatchers.notNullValue())
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(asstype.id))
            MatcherAssert.assertThat(loaded.id, CoreMatchers.`is`(asstype.id))

        }
    }

    @Test
    fun deleteAssesementType() = runBlockingTest {
        // Given a appointment inserted
        GlobalScope.launch(Dispatchers.IO) {
            val assType = AssessmentType(3,"Biology",84.3)
            database.assessmentTypeDao().insertAssessmentType(assType)

            // When deleting a appointment by id
            database.assessmentTypeDao().deleteAssessmentType(assType)

            // THEN - The object returned is null
            val getUser = database.assessmentTypeDao().getAssessmentTypeById(assType.id)
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())
        }
    }
    @Test
    fun updateAssesesmentType() = runBlockingTest {
        // When inserting an appointment
        GlobalScope.launch(Dispatchers.IO) {
            val original = AssessmentType(13,"History",56.4)
            database.assessmentTypeDao().updateAssessmentType(original)

            // When an appointment is updated
            val updated = AssessmentType(3,"Geography",34.4)
            database.assessmentTypeDao().updateAssessmentType(updated)

            // THEN - The loaded data contains the expected values
            val loaded = database.assessmentTypeDao().getAssessmentTypeById(original.id)
            MatcherAssert.assertThat(loaded.value?.id, CoreMatchers.`is`("3".toLong()))
            MatcherAssert.assertThat(loaded.value?.maximumPoint, CoreMatchers.`is`("34.4".toDouble()))


        }
    }
    @Test
    fun getAllAssessemType() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val assType = AssessmentType(3,"Histoy",65.4)
            database.assessmentTypeDao().insertAssessmentType(assType)
            val assType2 = AssessmentType(2,"English",56.4)
            database.assessmentTypeDao().insertAssessmentType(assType2)

            val result = database.assessmentTypeDao().getAllAssessmentTypes()

            MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
        }
    }
    @Test
    fun delateAllAssesType() = runBlockingTest {
        GlobalScope.launch(Dispatchers.IO) {
            val assType = AssessmentType(3,"Geo",56.3)
            database.assessmentTypeDao().insertAssessmentType(assType)
            val assType2 = AssessmentType(2,"Hist",98.0)
            database.assessmentTypeDao().insertAssessmentType(assType2)

            database.adminDao().deleteAllAdmins()

            val getUser = database.assessmentTypeDao().getAssessmentTypeById(assType.id)
            MatcherAssert.assertThat(getUser, CoreMatchers.notNullValue())

        }
    }


}

