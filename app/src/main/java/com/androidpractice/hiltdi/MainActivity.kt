package com.androidpractice.hiltdi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.androidpractice.hiltdi.Login.LoginContentScreen
import com.androidpractice.hiltdi.Login.RegisterContentScreen
import com.androidpractice.hiltdi.ui.theme.HiltDITheme
import com.androidpractice.hiltdi.util.Destination
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiltDITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HiltDITheme {
        Greeting("Android")
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = Destination.LoginScreen.route) {
        composable(Destination.LoginScreen.route) {
            LoginContentScreen(loginViewModel = hiltViewModel(), onRegisterNavigateTo = {
                navController.navigate(Destination.RegisterScreen.route)
            })
        }
        composable(Destination.RegisterScreen.route) {
            RegisterContentScreen(registerViewModel = hiltViewModel())

        }

//        composable(
//            route = Destination.BrowseRepositoryScreen.route +
//                    "/{org_name}",
//            arguments = listOf(navArgument("org_name") { type
//                = NavType.StringType }),
//            enterTransition = { scaleIn(tween(700)) },
//            exitTransition = { scaleOut(tween(700)) },
//        ) {
//            BrowseRepositoryScreen(
//                viewModel = hiltViewModel(),
//            )
//        }

    }
}

//sample navigation with arguments
/*SearchScreen(
viewModel = hiltViewModel(),
navigateToRepositoryScreen = { orgName ->
navController.navigate(
Destination.BrowseRepositoryScreen.route +
"/" + orgName
)
}
)
*/

