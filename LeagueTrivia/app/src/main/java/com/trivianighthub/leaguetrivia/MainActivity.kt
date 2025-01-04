package com.trivianighthub.leaguetrivia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trivianighthub.leaguetrivia.ui.auth.AuthScreen
import com.trivianighthub.leaguetrivia.ui.league.CreateLeagueScreen
import com.trivianighthub.leaguetrivia.ui.league.LeagueChoiceScreen
import com.trivianighthub.leaguetrivia.ui.theme.LeagueTriviaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeagueTriviaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(
                onNavigateToLeagueChoice = { navController.navigate("leagueChoice") }
            )
        }
        composable("leagueChoice") {
            LeagueChoiceScreen(
                onNavigateToCreateLeague = { navController.navigate("createLeague") }
            )
        }
        composable("createLeague") {
            CreateLeagueScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}