package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.Day2

fun main() {
    val inputReader = InputReader()
    val day2 = Day2(inputReader)

    val resultDay2 = day2.solve()
    println(
        """
    ====================== Day Two ======================
    Result of task 1: ${resultDay2.first}
    Result of task 2: ${resultDay2.second}
    """.trimIndent()
    )
}
