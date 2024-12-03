package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.Day3

fun main() {
    val inputReader = InputReader()
    val day3 = Day3(inputReader)

    val resultDay3 = day3.solve()
    println(
        """
    ===================== Day Three =====================
    Result of task 1: ${resultDay3.first}
    Result of task 2: ${resultDay3.second}
    """.trimIndent()
    )
}
