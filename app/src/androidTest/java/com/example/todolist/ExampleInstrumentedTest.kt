package com.example.todolist

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todolist.userInterface.activity.MainActivity
import com.example.todolist.userInterface.addTask.AddFragment

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.todolist", appContext.packageName)
    }
//    @Test
//    fun test(){
//        ActivityScenario.launch(MainActivity::class.java)
//        onView(withId(R.id.floatingActionButton)).perform(click())
//
//        onView(withId(R.id.et_task_name)).check(matches(isDisplayed()))
//        onView(withId(R.id.et_task_desc)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_add)).check(matches(isDisplayed()))
//    }
////    @get:Rule
////    val activityScenarioRule = activityScenarioRule<MainActivity>()
//    @Test
//    fun testAddButton(){
//        FragmentScenario.launchInContainer(AddFragment::class.java)
//        onView(withId(R.id.et_task_name)).perform(typeText("Task 1"), closeSoftKeyboard())
//        onView(withId(R.id.et_task_desc)).perform(typeText("Description 1"), closeSoftKeyboard())
//        onView(withId(R.id.btn_add)).perform(click())
//
//    }
}