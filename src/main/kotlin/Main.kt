package org.example

import org.example.inputReaders.InputReader
import org.example.puzzles.Day5

fun main() {
    val inputReader = InputReader()
    val day5 = Day5(inputReader)

    val resultDay5 = day5.solve()
    println(
        """
    ===================== Day Five ======================
    Result of task 1: ${resultDay5.first}
    Result of task 2: ${resultDay5.second}
    """.trimIndent()
    )
}
