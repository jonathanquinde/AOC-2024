import java.io.File
import kotlin.math.absoluteValue

val inputLines = File("src/resources/input1.txt").readLines()

fun puzzle1a() {
    var sum: Int = 0
    var columns: List<String>
    val left: MutableList<Int> = mutableListOf()
    val right: MutableList<Int> = mutableListOf()

    for (line in inputLines) {
        columns = line.split("   ", ignoreCase = true, limit = 2)
        left.add(columns[0].toInt())
        right.add(columns[1].toInt())
    }
    left.sort()
    right.sort()
    for (i in left.indices) {
        sum += (left[i] - right[i]).absoluteValue
    }
    println(sum)
}

fun puzzle1b() {
    var sum: Int = 0
    var multiplier: Int
    var columns: List<String>
    val left: MutableList<Int> = mutableListOf()
    val right: MutableList<Int> = mutableListOf()
    val inputLines: List<String> = File("src/input1.txt").readLines()

    for (line in inputLines) {
        columns = line.split("   ", ignoreCase = true, limit = 2)
        left.add(columns[0].toInt())
        right.add(columns[1].toInt())
    }
    for (lDigit in left) {
        multiplier = 0
        for (rDigit in right) {
            if (lDigit == rDigit)
                multiplier++
        }
        sum += lDigit * multiplier
    }
    println(sum)
}
