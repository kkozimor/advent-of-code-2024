package org.example.puzzles

import org.example.inputReaders.InputReader

/**
 *   [Advent of code 2024 - Day 9 puzzle](https://adventofcode.com/2024/day/9)
 */

class Day9(private val inputReader: InputReader) {

    fun solve(): Pair<Long, Int> {
        val input = inputReader.readStringInput(FILE_NAME).map { (it.toString()).toInt() }
        return taskOne(input) to taskTwo(input)
    }

    private fun taskOne(input: List<Int>): Long {
        val memory = mutableListOf<Int?>()
        var currentId = 0
        input.forEachIndexed{ index, it ->
            if(index % 2 == 0) {
                for(i in 0 until it)
                     memory.add(currentId)
                currentId++
            } else {
                for(i in 0 until it)
                    memory.add(null)
            }
        }
        //println(memory)
        deFragmentMemory(memory)
        println(memory)
        return computeChecksum(memory)
    }

    private fun taskTwo(input: List<Int>): Int {
        return 0
    }

    private fun deFragmentMemory(memory: MutableList<Int?>) {
        for (j in 0 until memory.size) {
            if(memory[j] != null) continue
            for(i in memory.size-1 downTo 0) {
                if (memory[j] == null && memory[i] != null && i > j) {
                    memory[j] = memory[i]
                    memory[i] = null
                    break
                }
            }
        }
    }

    private fun computeChecksum(memory: MutableList<Int?>): Long {
        return memory.mapIndexed{index, it ->
            if(it == null) 0L
            else (index*it).toLong()
        }.sumOf { it }
    }


    companion object {
        private const val FILE_NAME = "day9/input.txt"
    }
}
