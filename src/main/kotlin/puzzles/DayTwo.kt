package org.example.puzzles

import kotlin.math.abs

/**
 *   [Advent of code 2024 - Day 1 puzzle](https://adventofcode.com/2024/day/1)
 */

class DayTwo {

    fun taskOne(input: String): Int {
        return Regex("""mul\(([0-9]{1,3}),([0-9]{1,3})\)""")
            .findAll(input)
            .map { it.destructured.component1().toInt() to it.destructured.component2().toInt() }
            .sumOf { (a,b) -> a * b }
    }

    fun taskTwo(input: String): Int {
        val regexToRemoveBetween = Regex("""don't\(\).*?do\(\)""")
        val regexToRemoveAfterLast = Regex("""don't\(\).*""")
        val cleanInput = input
            .replace("\n", "")
            .replace(regexToRemoveBetween, "")
            .replace(regexToRemoveAfterLast, "")
        //println(cleanInput)
        return taskOne(cleanInput)
    }
}
