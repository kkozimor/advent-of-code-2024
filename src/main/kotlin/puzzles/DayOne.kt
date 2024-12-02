package org.example.puzzles

import kotlin.math.abs

/**
 *   [Advent of code 2024 - Day 1 puzzle](https://adventofcode.com/2024/day/1)
 */

class DayOne {

    fun taskOne(listOne: List<Int> , listTwo: List<Int>): Int {
        return listOne.sorted()
            .zip(listTwo.sorted())
            .sumOf { (a, b) -> abs(a - b) }
    }

    fun taskTwo(listOne: List<Int> , listTwo: List<Int>): Int {
        return listTwo
            .filter { it in listOne }
            .sum()
    }
}
