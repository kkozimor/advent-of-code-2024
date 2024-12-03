package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.DayOne
import org.example.puzzles.DayTwo

val FILE_NAME = "dayTwo/input.txt"

fun main() {
    val inputReader = InputReader()
    val dayTwo = DayTwo()

    val input = inputReader.readStringInput(FILE_NAME)

    println(
        dayTwo.taskOne(input)
    )
    println(
        dayTwo.taskTwo(input)
    )

//    val (listOne, listTwo) = inputReader.readIntPairsInput(FILE_NAME)
//
//    println(
//        dayOne.taskOne(listOne, listTwo)
//    )
//    println(
//        dayOne.taskTwo(listOne, listTwo)
//    )
}
