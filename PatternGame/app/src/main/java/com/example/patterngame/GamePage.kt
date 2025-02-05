package com.example.patterngame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
//import java.lang.reflect.Modifier
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@Composable
fun GamePage(navigationController: NavHostController) {
    var numSeq by remember { mutableStateOf(genSeq()) }
    var userInput by remember { mutableStateOf("") }
    var timeCt by remember { mutableStateOf(30) }
    var totalScore by remember { mutableStateOf(0) }
    var rightAns by remember { mutableStateOf(0) }
    var msg by remember { mutableStateOf("") }

    val gameTimer = remember { Timer() }
    val coRoutineFun = rememberCoroutineScope()

    LaunchedEffect(numSeq) {
        rightAns = numSeq.last() + getPattern(numSeq)
    }

    LaunchedEffect(Unit) {
        gameTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (timeCt > 0) {
                    timeCt -= 1
                } else {
                    gameTimer.cancel()
                    coRoutineFun.launch(Dispatchers.Main) {
                        navigationController.navigate("score/${totalScore}")
                    }

                }
            }
        }, 1000, 1000)

    }

    Box(modifier = Modifier.fillMaxSize().background(Color(255, 229, 180))) {
        IconButton(
            onClick = {
                gameTimer.cancel()
                navigationController.popBackStack("home", inclusive = false)
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Return")
        }
        //////////////External
        GamePageContent(
            numSeq = numSeq,
            userInput = userInput,
            timeCt = timeCt,
            totalScore = totalScore,
            msg = msg,
            onAnsInput = { userInput = it },
            onResponseSubmission = { response ->
                if (response == null) {
                    msg = "Invalid Response"
                } else if (check(response, rightAns)) {
                    totalScore += 5
                    numSeq = genSeq()
                    userInput = ""
                    //timeCt = 20
                    msg = "Correct"
                } else {
                    msg = "Incorrect"
                }
            }
        )

    }
}

@Composable
fun GamePageContent(
    numSeq: List<Int>,
    userInput: String,
    timeCt: Int,
    totalScore: Int,
    msg: String,
    onAnsInput: (String) -> Unit,
    onResponseSubmission: (Int?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        //.background(MaterialTheme.colorScheme.background),
        //.background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Top,
        //horizontalAlignment = Alignment.Start,
        //modifier = Modifier
        //  .fillMaxSize()
        //.background(MaterialTheme.colorScheme.background)
    ) {

        Text(text = "Current Score: $totalScore")

        Text(text = "Remaining Time: $timeCt")

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Guess Next Number: ${numSeq.joinToString(", ") + " ?"}",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = userInput,
            //onValueChange = { userInput = it },
            onValueChange = onAnsInput,
            label = { Text("Enter Response") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val inUsr = userInput.trim().toIntOrNull()
            onResponseSubmission(inUsr)
        }) {
            Text("Submit Guess")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = msg)
    }
}

