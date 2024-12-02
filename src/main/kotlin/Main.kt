package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.Day1

fun main() {
    val inputReader = InputReader()
    val day1 = Day1(inputReader)

    val resultDay1 = day1.solve()
    println(
        """
    ====================== Day One ======================
    Result of task 1: ${resultDay1.first}
    Result of task 2: ${resultDay1.second}
    """.trimIndent()
    )
}
