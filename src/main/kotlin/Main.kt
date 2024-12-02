package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.DayOne

val FILE_NAME = "dayOne/input.txt"

fun main() {
    val inputReader = InputReader()
    val dayOne = DayOne()

    val (listOne, listTwo) = inputReader.readIntPairsInput(FILE_NAME)

    println(
        dayOne.taskOne(listOne, listTwo)
    )
    println(
        dayOne.taskTwo(listOne, listTwo)
    )
}
