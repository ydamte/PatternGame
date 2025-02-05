package com.example.patterngame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.patterngame.ui.theme.PatternGameTheme
import com.example.patterngame.ui.theme.Typography
//import androidx.compose.material3.*

//Programmer: Yeabsera Damte
//Date: 10/11/2024
//Android Studio Koala | 2024.1.1
//macOS Sonoma 14.4.1
//Description: This app is an interactive Game where the user must guess the next number
// in a sequence of given numbers based on a pattern.
//The player has 30 seconds per game and gets 5 points for each correct guess.
//The total score is displayed once the timer runs out and the game ends
//Citations: For Algorithms and Ideas for Design Structures =>
//https://www.geeksforgeeks.org/guessing-the-number-game-using-android-studio/
//https://developer.android.com/develop/ui/compose/documentation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PatternGameTheme {
                val navigationController = rememberNavController()
                NavigationComponent(navigationController)
            }
        }
    }
}



@Composable
fun NavigationComponent(navigationController: NavHostController) {

        NavHost(navigationController, startDestination = "home") {
            composable("home") { HomePage(navigationController) }
            composable("game") { GamePage(navigationController) }
            composable("score/{totalScore}") { navBackStackEntry ->
                Results(
                    navigationController,
                    totalScore = navBackStackEntry.arguments?.getString("totalScore") ?: "0"
                )
            }
        }

}