package com.schoolfam.parcher.UITEST.EndToEndTest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.schoolfam.parcher.Fragments.TeacherFragments.TeacherPostAssessmentFragment
import com.schoolfam.parcher.R
import org.hamcrest.CoreMatchers
import org.junit.Test

class Assessment {
    @Test
    fun postAssessment(){
        val scenario = launchFragmentInContainer<TeacherPostAssessmentFragment>()

        Espresso.onView(ViewMatchers.withId(R.id.subject_spinner)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.anything()).atPosition(0).perform(ViewActions.click())
        ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed())
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.select_subject_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.select_subject_button)).perform(ViewActions.click())
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.assessment_type_spinner)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.anything()).atPosition(1).perform(ViewActions.click())
        ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed())
        Thread.sleep(2000)


        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.assessment_score_edit_text)).perform(ViewActions.typeText("9")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.post_assessment_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)

    }
}