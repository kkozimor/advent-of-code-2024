package org.example.puzzles

import org.example.inputReaders.InputReader
import kotlin.math.abs

/**
 *   [Advent of code 2024 - Day 2 puzzle](https://adventofcode.com/2024/day/12
 */

class Day2(private val inputReader: InputReader) {

    fun solve(): Pair<Int, Int> {
        val input = inputReader.lazyReadIntArrayInput(FILE_NAME)
        return taskOne(input) to taskTwo(input)
    }

    private fun taskOne(input: Sequence<List<Int>>): Int {
        return input.count { report -> isSafe(report)
        }
    }

    private fun taskTwo(input: Sequence<List<Int>>): Int {
        return input.count { report ->
            isSafe(report) || isSafe(fixReport(report.toMutableList()))
        }
    }

    private fun isSafe(report: List<Int>): Boolean {
        val isIncreasing = report.zipWithNext().all { (a, b) -> a < b && abs(a - b) in 1..3 }
        val isDecreasing = report.zipWithNext().all { (a, b) -> a > b && abs(a - b) in 1..3 }
        return isIncreasing || isDecreasing
    }

    private fun fixReport(report: MutableList<Int>): List<Int> {
        for (i in report.indices) {
            val tempList = report.toMutableList().apply { removeAt(i) }
            if (isSafe(tempList)) {
                report.removeAt(i)
                break
            }
        }
        return report
    }

    companion object {
        private const val FILE_NAME = "day2/input.txt"
    }
}
