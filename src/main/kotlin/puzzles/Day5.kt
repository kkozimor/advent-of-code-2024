package org.example.puzzles

import org.example.inputReaders.InputReader

/**
 *   [Advent of code 2024 - Day 5 puzzle](https://adventofcode.com/2024/day/5)
 */

class Day5(private val inputReader: InputReader) {

    fun solve(): Pair<Int, Int> {
        val input = inputReader.lazyReadStringLines(FILE_NAME)
        val (instructions, updates) = readInstructionsAndUpdates(input)
        return taskOne(instructions, updates) to taskTwo(instructions, updates)
    }

    private fun taskOne(instructions: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
        return updates.sumOf { update ->
            if (generatePairs(update).none { (a, b) -> instructions.contains(b to a) }) {
                update[(update.size) / 2]
            } else 0

        }
    }

    private fun taskTwo(instructions: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
        return updates.filter { update ->
            generatePairs(update).any { instructions.contains(it.second to it.first) }
        }.sumOf { update ->
            val fixedUpdate = fixUpdate(instructions, update)
            fixedUpdate[fixedUpdate.size / 2]
        }
    }

    private fun fixUpdate (instructions: List<Pair<Int, Int>>, update: List<Int>): List<Int> {
        val conflict = generatePairs(update).firstOrNull { instructions.contains(it.second to it.first) }
        return if (conflict == null) update
        else {
            val (a, b) = conflict
            val fixedUpdate = update.toMutableList().apply {
                val indexOfA = update.indexOf(a)
                val indexOfB = update.indexOf(b)
                this[indexOfA] = b
                this[indexOfB] = a
            }
            fixUpdate(instructions, fixedUpdate)
        }
    }

    private fun generatePairs(update: List<Int>): List<Pair<Int, Int>> =
        update.indices.flatMap { i ->
            (i + 1 until update.size).map { j -> update[i] to update[j] }
        }


    private fun readInstructionsAndUpdates(input: Sequence<String>): Pair<List<Pair<Int,Int>>, List<List<Int>>> {
        val delimiterIndex = input.indexOf("")
        val instructions = input.take(delimiterIndex).map {
            it.split('|').let { parts -> parts[0].toInt() to parts[1].toInt() }
        }.toList()

        val updates = input.drop(delimiterIndex + 1).map {
            it.split(',').map(String::toInt)
        }.toList()

        return instructions to updates
    }

    companion object {
        private const val FILE_NAME = "day5/input.txt"
    }
}
