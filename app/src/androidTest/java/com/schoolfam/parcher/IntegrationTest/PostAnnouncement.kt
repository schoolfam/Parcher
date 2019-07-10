package com.schoolfam.parcher.UITEST.IntegrationTest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.Fragments.AdminFragments.PostAnnouncementFragment
import com.schoolfam.parcher.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)

class PostAnnouncement {

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
        Espresso.onView(ViewMatchers.withId(com.schoolfam.parcher.R.id.announcement_title_edit_text)).perform(ViewActions.typeText("hello")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.pressBack()
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(com.schoolfam.parcher.R.id.announcement_desc_edit_text)).perform(ViewActions.typeText("hello")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())

              //  .check(matches(withText(startsWith("Number of repos found: 668755"))));
        )
        Espresso.pressBack()
        Thread.sleep(2000)
       var teview= Espresso.onView(ViewMatchers.withId(com.schoolfam.parcher.R.id.post_announcement_button)).check(ViewAssertions.matches(ViewMatchers.isClickable()))

        Thread.sleep(5000)



    }
}