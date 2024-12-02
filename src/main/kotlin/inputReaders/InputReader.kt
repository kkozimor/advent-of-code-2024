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
}
