package com.androidpractice.hiltdi.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidpractice.hiltdi.Login.components.LoginButton
import com.androidpractice.hiltdi.Login.components.PasswordInputField
import com.androidpractice.hiltdi.Login.components.UserNameField
import com.androidpractice.hiltdi.R
import com.androidpractice.hiltdi.util.TestTags
import com.androidpractice.hiltdi.util.TestTags.LoginContent.LOGO_IMAGE

@Composable
fun TestTags.LoginContent(
    modifier: Modifier = Modifier,
    uiState: AuthenticationState,
    onUsernameUpdated: (String) -> Unit,
    onPasswordUpdated: (String) -> Unit,
    onLogin: () -> Unit,
    passwordToggleVisibility: (Boolean) -> Unit,
    onRegister: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.purple_700)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = modifier
                    .testTag(LOGO_IMAGE),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo"
            )

        }

        Card(
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            modifier = modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.purple_700))
                .weight(5.0f),
//            elevation = CardElevation(4.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .scrollable(scrollState, Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                UserNameField(authState = uiState, onValueChanged = onUsernameUpdated)
                PasswordInputField(
                    text = "Password",
                    authState = uiState,
                    onValueChanged = onPasswordUpdated,
                    passwordToggleVisibility = passwordToggleVisibility
                )
                LoginButton(
                    text = "Sign In",
                    enabled = if (uiState.isValidForm()) {
                        !uiState.loading
                    } else {
                        false
                    },
                    onLoginClicked = {
                        onLogin.invoke()
                    },
                    isLoading = uiState.loading
                )

                ClickableText(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .testTag(TestTags.LoginContent.REGISTER_USER),
                    text = AnnotatedString("First time user? Sign UP"),
                    onClick = { onRegister.invoke() },
                    style = TextStyle(
                        colorResource(id = R.color.purple_700),
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Composable
fun CheckableSwitch() {
    Switch(checked = false, onCheckedChange = {})
}

@Composable
fun NonCheckableSwitch() {
    Switch(checked = false, onCheckedChange = null)
}
