package com.schoolfam.parcher.UITEST.IntegrationTest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.Fragments.AdminFragments.AddParentFragment
import com.schoolfam.parcher.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class RegisterParent {



    @Test
    fun RegisterParent() {
        val scenario = launchFragmentInContainer<AddParentFragment>()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.parent_first_name_edit_text)).perform(ViewActions.typeText("berhanu")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_last_name_edit_text)).perform(ViewActions.typeText("Kumsa")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_user_name_edit_text)).perform(ViewActions.typeText("berhanu")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_email_edit_text)).perform(ViewActions.typeText("berhanu@gmail.com")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_password_edit_text)).perform(ViewActions.typeText("132456")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.parent_phone_edit_text)).perform(ViewActions.typeText("0912313213")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.parent_gender_radio_group))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))

        Espresso.onView(ViewMatchers.withId(R.id.parent_register_button)).perform(ViewActions.scrollTo())
        Espresso.onView(ViewMatchers.withId(R.id.parent_register_button)).perform(ViewActions.scrollTo())




    }
}