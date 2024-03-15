package com.androidpractice.hiltdi

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.androidpractice.hiltdi.ui.theme.HiltDITheme
import com.androidpractice.hiltdi.util.TestTags
import com.androidpractice.hiltdi.util.TestTags.LoginContent.SIGN_IN_BUTTON
import com.androidpractice.hiltdi.util.TestTags.LoginContent.USERNAME_FIELD
import org.junit.Rule
import org.junit.Test


class LoginContentTest {
    @get:Rule
    val composeRuleTest = createAndroidComposeRule<MainActivity>()

//    private lateinit var navController: NavHostController
    @Test
    fun assertSignInButtonIsDisplayed() {
        initCompose()
        composeRuleTest.onNodeWithTag(SIGN_IN_BUTTON, true).assertIsDisplayed()
    }

    @Test
    fun assertUserInputFieldIsDisplayed() {
        initCompose()
        composeRuleTest.onNodeWithTag(USERNAME_FIELD, true).assertIsDisplayed()
    }

//    @Test
//    fun assertRegisterClickableButtonNavigatesToRegisterScreen() {
//        initCompose()
//        composeRuleTest.onNodeWithTag(TestTags.LoginContent.REGISTER_USER).performClick()
//
//        val route = navController.currentDestination?.route
//        assert(route.equals(Destination.RegisterScreen.route))
//
//    }

    private fun initCompose() {
        composeRuleTest.activity.setContent {
            HiltDITheme {
                TestTags.LoginContent
            }
        }
    }
}