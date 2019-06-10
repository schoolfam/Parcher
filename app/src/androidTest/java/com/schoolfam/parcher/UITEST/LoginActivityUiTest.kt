package com.schoolfam.parcher.UITEST
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.schoolfam.parcher.LoginActivity
import com.schoolfam.parcher.R
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityUiTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)


    private val email="sinkumen@gmail.com"
    private val password ="123456"

    @Test
    fun clickLoginButton_opensLoginUi() {
        Thread.sleep(2000)
        onView(withId(R.id.email_edit_text)).perform(ViewActions.typeText(email)).check(matches(isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)
        onView(withId(R.id.password_edit_text)).perform(ViewActions.typeText(password)).check(matches(isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.login_button)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        Espresso.onView(withId(R.id.login_button)).perform(click())
}


}