package com.schoolfam.parcher.UITEST

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.Fragments.AdminFragments.AddParentFragment
import com.schoolfam.parcher.Fragments.AdminFragments.AddStudentsFragment
import com.schoolfam.parcher.Fragments.AdminFragments.AddTeachersFragment
import com.schoolfam.parcher.LoginActivity
import com.schoolfam.parcher.MainActivity
import com.schoolfam.parcher.R
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class FragmentTesting {
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
        Espresso.onView(ViewMatchers.withId(R.id.student_first_name_edit_text)).perform(ViewActions.typeText("helo")).check(
        matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.student_last_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.student_user_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.student_email_edit_text)).perform(ViewActions.typeText("helo")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(withId(R.id.student_password_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(withId(R.id.section_spinner)).perform(click())
        Espresso.onData(anything()).atPosition(1).perform(click())
            matches(isCompletelyDisplayed())
        Thread.sleep(2000)

        Espresso.onView(withId(R.id.parent_spinner)).check(ViewAssertions.matches(isClickable()))
       matches(isClickable())
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.gender_radio_group))
            .check(matches(isEnabled()))
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.student_register_button)).check(matches(isFocusable()))
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.student_register_button)).perform(click())

    }


    @Test
    fun RegisterParent() {
        val scenario = launchFragmentInContainer<AddParentFragment>()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.parent_first_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_last_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_user_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_email_edit_text)).perform(ViewActions.typeText("helo")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(withId(R.id.parent_password_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(withId(R.id.parent_phone_edit_text)).perform(ViewActions.typeText("0912313213")).check(
            matches(isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.parent_gender_radio_group))
            .check(matches(isEnabled()))

        Espresso.onView(withId(R.id.parent_register_button)).perform(ViewActions.click())
            .check(matches(isClickable()))
            .check(matches(isFocusable()))

    }
    @Test
    fun RegisterTeacher() {
        val scenario = launchFragmentInContainer<AddTeachersFragment>()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.teacher_first_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.teacher_last_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.teacher_user_name_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.teacher_email_edit_text)).perform(ViewActions.typeText("helo")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(withId(R.id.teacher_password_edit_text)).perform(ViewActions.typeText("helo")).check(
            matches(isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(withId(R.id.teacher_section_spinner)).perform(click())
        Espresso.onData(anything()).atPosition(1).perform(click())
        matches(isCompletelyDisplayed())
        Thread.sleep(2000)

        Thread.sleep(2000)
        Espresso.onView(withId(R.id.teacher_gender_radio_group))
            .check(matches(isEnabled()))


        Espresso.onView(withId(R.id.teacher_register_button)).perform(click())

    }
}