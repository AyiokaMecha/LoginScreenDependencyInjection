package com.androidpractice.hiltdi.Login

import LoginViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.androidpractice.hiltdi.util.TestTags
import kotlinx.coroutines.flow.Flow

@Composable
fun LoginContentScreen(
    loginViewModel: LoginViewModel,
    onRegisterNavigateTo: () -> Unit
) {
    val viewState: AuthenticationState by loginViewModel.state.collectAsState()

    TestTags.LoginContent(
        uiState = viewState,
        onUsernameUpdated = loginViewModel::userNameChanged,
        onPasswordUpdated = loginViewModel::passwordChanged,
        onLogin = loginViewModel::login,
        passwordToggleVisibility = loginViewModel::passwordVisibility,
        onRegister = onRegisterNavigateTo
    )

}