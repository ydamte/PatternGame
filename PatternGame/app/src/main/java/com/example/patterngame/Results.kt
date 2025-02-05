package com.example.patterngame


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
*/


@Composable
fun Results(navigationController: NavHostController, totalScore: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(MaterialTheme.colorScheme.background)
            .background(Color(255, 229, 180)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text="Game Over")
        Spacer(modifier=Modifier.height(20.dp))

        Text(text="Your Score: $totalScore")
        Spacer(modifier=Modifier.height(20.dp))

        Button(onClick = {
            //navigationController.navigate("home")
            navigationController.popBackStack("home", inclusive = false)
        }){
            Text("Play Again")
        }
    }
}