package com.schoolfam.parcher.UITEST.IntegrationTest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.Fragments.AdminFragments.AddTeachersFragment
import com.schoolfam.parcher.MainActivity
import com.schoolfam.parcher.R
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class RegisterTeacher {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    //val fragment: AddStudentsFragment? = null
    @Before
    fun init() {
        activityRule.activity.supportFragmentManager.beginTransaction()
    }
    @Test
    fun RegisterTeacher() {
        val scenario = launchFragmentInContainer<AddTeachersFragment>()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.teacher_first_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.teacher_last_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.teacher_user_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.teacher_email_edit_text)).perform(ViewActions.typeText("helo")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.teacher_password_edit_text)).perform(ViewActions.typeText("helo")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.teacher_section_spinner)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.anything()).atPosition(1).perform(ViewActions.click())
        ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed())
        Thread.sleep(2000)

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.teacher_gender_radio_group))
            .perform(ViewActions.scrollTo(), ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.teacher_gender_radio_group))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        Espresso.onView(ViewMatchers.withId(R.id.teacher_register_button)).perform(ViewActions.scrollTo())

    }
}