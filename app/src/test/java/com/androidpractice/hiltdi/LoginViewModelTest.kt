package com.androidpractice.hiltdi

import LoginViewModel
import androidx.lifecycle.SavedStateHandle
import com.androidpractice.hiltdi.Login.AuthenticationState
import com.androidpractice.hiltdi.util.SampleLoginDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel
    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(
            dispatchers = SampleLoginDispatchers.createTestDispatchers(
                UnconfinedTestDispatcher()
            ),
            stateHandle = SavedStateHandle()
        )
    }

    @Test
    fun `test authentication state changes`() = runTest {
        loginViewModel.userNameChanged("Madona")
        loginViewModel.passwordChanged("home")
        loginViewModel.passwordVisibility(true)
        loginViewModel.state.test {
            val stateChange = awaitItem()
            Truth.assertThat(stateChange).isEqualTo(
                AuthenticationState(
                    userName = "Madona",
                    password = "home",
                    togglePasswordVisibility = true
                )
            )
        }
    }
}