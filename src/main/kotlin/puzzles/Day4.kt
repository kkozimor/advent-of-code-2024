package org.example.puzzles

import org.example.inputReaders.InputReader

/**
 *   [Advent of code 2024 - Day 4 puzzle](https://adventofcode.com/2024/day/4)
 */

class Day4(private val inputReader: InputReader) {

    fun solve(): Pair<Int, Int> {
        val input = inputReader.lazyReadStringLines(FILE_NAME).map { it.toList() }.toList()
        return taskOne(input) to taskTwo(input)
    }

    private fun taskOne(input: List<List<Char>>): Int {
        var foundKeywords = 0
        iterateInput(input) { row ->
            foundKeywords += row.windowed(4).count { it == XMAS || it == XMAS.reversed() }
        }
        return foundKeywords
    }

    private fun taskTwo(input: List<List<Char>>): Int {
        return getSubMatricesCrossingAtA(input).count { matrix ->
            val (tl, tr, bl, br) = listOf(matrix[0][0], matrix[0][2], matrix[2][0], matrix[2][2])
            (tl == 'M' && br == 'S' || tl == 'S' && br == 'M') &&
                    (tr == 'M' && bl == 'S' || tr == 'S' && bl == 'M')
        }
    }

    private fun getSubMatricesCrossingAtA(input: List<List<Char>>): List<List<List<Char>>> {
        val subMatrices = mutableListOf<List<List<Char>>>()
        for (i in 1 until input.size - 1) {
            for (j in 1 until input[i].size - 1) {
                if (input[i][j] == 'A') {
                    val subMatrix = (i - 1..i + 1).map { row ->
                        input[row].subList(j - 1, j + 2)
                    }
                    subMatrices.add(subMatrix)
                }
            }
        }
        return subMatrices
    }

    private fun iterateInput(matrix: List<List<Char>>, action: (List<Char>) -> Unit) {
        listOf(
            ::iterateRows,
            ::iterateColumns,
            ::iterateDiagonalsTLBR,
            ::iterateDiagonalsTRBL
        ).forEach { it(matrix, action) }
    }

    private fun iterateRows(matrix: List<List<Char>>, action: (List<Char>) -> Unit) {
        matrix.forEach(action)
    }

    private fun iterateColumns(matrix: List<List<Char>>, action: (List<Char>) -> Unit) {
        for (col in matrix[0].indices) {
            action(matrix.map { it[col] })
        }
    }

    private fun iterateDiagonalsTLBR(matrix: List<List<Char>>, action: (List<Char>) -> Unit) {
        for (d in -(matrix.size - 1) until matrix[0].size) {
            action(matrix.mapIndexedNotNull { row, _ -> matrix.getOrNull(row)?.getOrNull(row + d) })
        }
    }

    private fun iterateDiagonalsTRBL(matrix: List<List<Char>>, action: (List<Char>) -> Unit) {
        for (d in 0 until (matrix.size + matrix[0].size - 1)) {
            action(matrix.mapIndexedNotNull { row, _ -> matrix.getOrNull(row)?.getOrNull(d - row) })
        }
    }

    companion object {
        private const val FILE_NAME = "day4/input.txt"
        private val XMAS = "XMAS".toList()
    }
}
