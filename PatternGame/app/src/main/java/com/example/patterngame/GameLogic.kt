package com.example.patterngame

fun genSeq(): List<Int> {
    val firstVal = (1..10).random()
    //val pattern = getPattern(emptyList())
    val patternList = listOf(2, 5, -10, -2, 15, -5)
    val pattern = patternList.random()

    return List(4) {firstVal + it * pattern}
}

fun getPattern(sequence: List<Int>): Int {
    //return listOf(1,2,-1,-2,3,-3).random()
    return if(sequence.size > 1){
        sequence[sequence.size - 1] - sequence[sequence.size - 2]
    } else {
        1
    }
}

fun check(userInput: Int, correct: Int): Boolean {
    return userInput == correct
}