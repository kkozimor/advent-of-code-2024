package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.Day9

fun main() {
    val inputReader = InputReader()
    val day9 = Day9(inputReader)

    val resultDay9 = day9.solve()
    println(
        """
    ====================== Day Nine =====================
    Result of task 1: ${resultDay9.first}
    Result of task 2: ${resultDay9.second}
    """.trimIndent()
    )
}
