package hu.ait.highlowgamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hu.ait.highlowgamecompose.ui.screen.game.GameScreen
import hu.ait.highlowgamecompose.ui.screen.help.HelpScreen
import hu.ait.highlowgamecompose.ui.screen.mainmenu.MainScreen
import hu.ait.highlowgamecompose.ui.theme.HighLowGameComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HighLowGameComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "mainmenu"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("mainmenu") { MainScreen(
            onNavigateToGame = {navController.navigate("game")},
            navController = navController
        )}
        //composable("game") { GameScreen()}
        composable("game?upperBound={upperBound}",
            arguments = listOf(navArgument("upperBound") {
                defaultValue = 0
                type = NavType.IntType })
        ) { GameScreen() }

        composable("helpscreen/{helptext}",
            arguments = listOf(navArgument("helptext"){type = NavType.StringType})
            ) {

            val helpText = it.arguments?.getString("helptext")
            helpText?.let {
                HelpScreen(helpText = helpText)
            }
        }
    }
}
