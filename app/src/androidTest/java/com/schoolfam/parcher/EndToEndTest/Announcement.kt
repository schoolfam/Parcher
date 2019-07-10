package com.schoolfam.parcher.UITEST.EndToEndTest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.Fragments.AdminFragments.PostAnnouncementFragment
import com.schoolfam.parcher.MainActivity
import com.schoolfam.parcher.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class Announcement {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    //val fragment: AddStudentsFragment? = null
    @Before
    fun init() {
        activityRule.activity.supportFragmentManager.beginTransaction()
    }




    @Test
    fun postAnnouncement() {
        val scenario = launchFragmentInContainer<PostAnnouncementFragment>()



        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.announcement_title_edit_text)).perform(ViewActions.typeText("title one")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.announcement_desc_edit_text)).perform(ViewActions.typeText("the first description .....")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.post_announcement_button)).check(ViewAssertions.matches(ViewMatchers.isClickable()))
        Thread.sleep(500)


    }
}