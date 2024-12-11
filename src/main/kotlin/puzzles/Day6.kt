package org.example.puzzles

import org.example.inputReaders.InputReader

/**
 *   [Advent of code 2024 - Day 6 puzzle](https://adventofcode.com/2024/day/6)
 */

class Day6(private val inputReader: InputReader) {

    fun solve(): Pair<Int, Int> {
        val rawMapInput = inputReader.lazyReadStringLines(FILE_NAME).map { it.toList() }.toList()
        return taskOne(rawMapInput) to taskTwo(rawMapInput)
    }

    private fun taskOne(rawMapInput: List<List<Char>>): Int {
        val labMap = setupLabMap(rawMapInput)
        while(labMap.isGuardInTheLab()) {
            labMap.takeGuardTurn()
        }
        return labMap.countVisitedFields()
    }

    private fun taskTwo(rawMapInput: List<List<Char>>): Int {
        var labMap = setupLabMap(rawMapInput)
        while(labMap.isGuardInTheLab()) {
            labMap.takeGuardTurn()
        }
        var newLabMap: LabMap
        return labMap
            .guardPatrolHistory
            .distinctBy { it.position }
            .filter { rawMapInput[it.position.first][it.position.second] == '.' }
            .count {
                newLabMap = LabMap(rawMapInput, newObstaclePosition = it.position.first to it.position.second)
                while(newLabMap.isGuardInTheLab()) {
                    if(newLabMap.isGuardLooped()){
                        println("Correctly looped obstacle position is: ${newLabMap.newObstaclePosition}")
                        println()
                        return@count true
                    }
                    newLabMap.takeGuardTurn()
                }
                false
            }
    }

    private fun setupLabMap(rawMapInput: List<List<Char>>): LabMap {
        return LabMap(rawMapInput)
    }

    companion object {
        private const val FILE_NAME = "day6/input.txt"

        private class LabMap(
            val rawMapInput: List<List<Char>>,
            val guardPatrolHistory: MutableSet<VisitedField> = mutableSetOf(),
            var newObstaclePosition: Pair<Int, Int>? = null,
            val obstacles: List<Pair<Int, Int>> = findObstacles(rawMapInput, newObstaclePosition),
            var guardPosition: Pair<Int, Int> = findGuardInitialPosition(rawMapInput),
            var guardDirection: DirectionEnum = findGuardInitialDirection(rawMapInput, guardPosition)
        ) {
            fun takeGuardTurn() {
                val guardNextPosition = getGuardNextPosition()
                if(isFacingObstacle(guardNextPosition)) turnGuard()
                else {
                    guardPatrolHistory.add(VisitedField((guardPosition.first to guardPosition.second), guardDirection))
                    stepForward(guardNextPosition)
                }
            }

            fun turnGuard() {
                guardDirection = when (guardDirection) {
                    DirectionEnum.UP -> DirectionEnum.RIGHT
                    DirectionEnum.RIGHT -> DirectionEnum.DOWN
                    DirectionEnum.DOWN -> DirectionEnum.LEFT
                    DirectionEnum.LEFT -> DirectionEnum.UP
                }
            }

            fun isGuardLooped(): Boolean {
                return guardPatrolHistory.any{ it.compare(guardPosition, guardDirection) }
            }

            private fun stepForward(guardNextPosition: Pair<Int, Int>) {
                guardPosition = guardNextPosition
            }

            private fun isFacingObstacle(guardNextPosition: Pair<Int, Int>): Boolean {
                return obstacles.any { it == guardNextPosition }
            }

            private fun getGuardNextPosition(): Pair<Int, Int> {
                return when(guardDirection) {
                    DirectionEnum.UP -> guardPosition.first-1 to guardPosition.second
                    DirectionEnum.RIGHT -> guardPosition.first to guardPosition.second+1
                    DirectionEnum.DOWN -> guardPosition.first+1 to guardPosition.second
                    DirectionEnum.LEFT -> guardPosition.first to guardPosition.second-1
                }
            }

            fun isGuardInTheLab(): Boolean {
                val (col, row) = guardPosition
                try{
                    rawMapInput[col][row]
                    return true
                } catch(e: Exception){
                    return false
                }
            }

            fun countVisitedFields(): Int {
                return guardPatrolHistory
                    .map { it.position }
                    .distinct()
                    .count()
            }
        }

        private fun findObstacles(rawMapInput: List<List<Char>>, newObstaclePosition: Pair<Int, Int>? = null): List<Pair<Int, Int>> {
            val obstacles = rawMapInput.flatMapIndexed { i, cols ->
                cols.mapIndexedNotNull { j, mapElement ->
                    if (mapElement == '#') i to j
                    else null
                }
            }.toMutableList()
            newObstaclePosition?.let { obstacles.add(newObstaclePosition) }
            return obstacles
        }

        private fun findGuardInitialPosition(rawMapInput: List<List<Char>>): Pair<Int, Int> {
            for (i in rawMapInput.indices) {
                for (j in rawMapInput[i].indices) {
                    if (DirectionEnum.entries.any { it.directionChar == rawMapInput[i][j] })
                        return i to j
                }
            }
            throw Error("Guard not found!")
        }

        private fun findGuardInitialDirection(
            rawMapInput: List<List<Char>>,
            guardInitialPosition: Pair<Int, Int>
        ): DirectionEnum {
            return DirectionEnum.entries.first { it.directionChar == rawMapInput[guardInitialPosition.first][guardInitialPosition.second] }
        }

        private data class VisitedField(
            val position: Pair<Int, Int>,
            val direction: DirectionEnum
        ) {
            fun compare(position: Pair<Int, Int>, direction: DirectionEnum): Boolean {
                return this.position == position && this.direction == direction
            }
        }


        enum class DirectionEnum(val directionChar: Char) {
            UP('^'),
            DOWN('v'),
            RIGHT('>'),
            LEFT('<');
        }
    }
}
