package org.example.inputReaders

import java.io.File

class InputReader {
    fun readIntPairsInput(fileName: String): Pair<List<Int>, List<Int>>  {
        return File("src/main/resources/$fileName").useLines { lines ->
            lines
                .map { line ->
                    line
                        .split("\\s+".toRegex())
                        .let { it[0].toInt() to it[1].toInt() }
                }
                .unzip()
        }
    }

    fun lazyReadIntArrayInput(fileName: String): Sequence<List<Int>> = sequence {
        File("src/main/resources/$fileName").useLines { lines ->
            lines.forEach {
                yield(it.split("\\s+".toRegex()).map { it.toInt() })
            }
        }
    }

    fun readStringInput(fileName: String): String {
        return File("src/main/resources/$fileName").readText()
    }
}
