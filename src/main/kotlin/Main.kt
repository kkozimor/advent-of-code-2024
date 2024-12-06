package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.Day4

fun main() {
    val inputReader = InputReader()
    val day4 = Day4(inputReader)

    val resultDay4 = day4.solve()
    println(
        """
    ====================== Day Four =====================
    Result of task 1: ${resultDay4.first}
    Result of task 2: ${resultDay4.second}
    """.trimIndent()
    )
}
