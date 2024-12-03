package org.example.puzzles

import org.example.inputReaders.InputReader

/**
 *   [Advent of code 2024 - Day 3 puzzle](https://adventofcode.com/2024/day/3)
 */

class Day3(private val inputReader: InputReader) {

    fun solve(): Pair<Int, Int> {
        val input = inputReader.readStringInput(FILE_NAME)
        return taskOne(input) to taskTwo(input)
    }

    private fun taskOne(input: String): Int {
        return Regex("""mul\(([0-9]{1,3}),([0-9]{1,3})\)""")
            .findAll(input)
            .map { it.destructured.component1().toInt() to it.destructured.component2().toInt() }
            .sumOf { (a,b) -> a * b }
    }

    private fun taskTwo(input: String): Int {
        val removeDisabledCommandsRegex = Regex("""don't\(\).*?(do\(\)|${'$'})""")
        val cleanInput = input
            .replace("\n", "")
            .replace(removeDisabledCommandsRegex , "")
        return taskOne(cleanInput)
    }

    companion object {
        private const val FILE_NAME = "day3/input.txt"
    }
}
