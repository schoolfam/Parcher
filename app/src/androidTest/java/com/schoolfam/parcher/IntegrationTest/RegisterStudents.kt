package com.schoolfam.parcher.UITEST.IntegrationTest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.Fragments.AdminFragments.AddStudentsFragment
import com.schoolfam.parcher.MainActivity
import com.schoolfam.parcher.R
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterStudents {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    //val fragment: AddStudentsFragment? = null
    @Before
    fun init() {
        activityRule.activity.supportFragmentManager.beginTransaction()
    }


    @Test
    fun RegisterStudent() {
        val scenario = launchFragmentInContainer<AddStudentsFragment>()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.student_first_name_edit_text)).perform(ViewActions.typeText("george")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.student_last_name_edit_text)).perform(ViewActions.typeText("berhanu")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.student_user_name_edit_text)).perform(ViewActions.typeText("@george")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.student_email_edit_text)).perform(ViewActions.typeText("george@gmail.com")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.student_password_edit_text)).perform(ViewActions.typeText("123456")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.section_spinner)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.anything()).atPosition(1).perform(ViewActions.click())
        ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed())
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_spinner)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
        ViewAssertions.matches(ViewMatchers.isClickable())
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.gender_radio_group))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.student_register_button)).check(ViewAssertions.matches(ViewMatchers.isFocusable()))
        Thread.sleep(2000)



    }
}