package org.example.puzzles

import org.example.inputReaders.InputReader
import kotlin.math.abs

/**
 *   [Advent of code 2024 - Day 1 puzzle](https://adventofcode.com/2024/day/1)
 */

class Day1(private val inputReader: InputReader) {

    fun solve(): Pair<Int, Int> {
        val input = inputReader.readIntPairsInput(FILE_NAME)
        return taskOne(input.first, input.second) to taskTwo(input.first, input.second)
    }

    private fun taskOne(listOne: List<Int> , listTwo: List<Int>): Int {
        return listOne.sorted()
            .zip(listTwo.sorted())
            .sumOf { (a, b) -> abs(a - b) }
    }

    private fun taskTwo(listOne: List<Int> , listTwo: List<Int>): Int {
        return listTwo
            .filter { it in listOne }
            .sum()
    }

    companion object {
        private const val FILE_NAME = "day1/input.txt"
    }
}
