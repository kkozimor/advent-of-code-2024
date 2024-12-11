package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.Day6

fun main() {
    val inputReader = InputReader()
    val day6 = Day6(inputReader)

    val resultDay6 = day6.solve()
    println(
        """
    ====================== Day Six ======================
    Result of task 1: ${resultDay6.first}
    Result of task 2: ${resultDay6.second}
    """.trimIndent()
    )
}
