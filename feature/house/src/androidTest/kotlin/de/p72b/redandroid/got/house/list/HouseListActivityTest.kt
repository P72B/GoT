package de.p72b.redandroid.got.house.list

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.p72b.redandroid.got.house.baseHouseModules
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
class HouseListActivityTest: KoinTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HouseListActivity>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(baseHouseModules)
    }

    // TODO test will fail because of java.lang.IllegalStateException: KoinApplication has not been started
    @Test
    fun usesCorrectContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("de.p72b.redandroid.got.house.test", appContext.packageName)
    }
}