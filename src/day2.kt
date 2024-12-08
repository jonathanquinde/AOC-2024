import java.io.File
import kotlin.math.absoluteValue

val file2: List<String> = File("src/resources/input2.txt").readLines()

fun processLevel(level: List<Int>): Boolean {
    var isIncrementing = false

    if (level[1] > level[0])
        isIncrementing = true
    for (i in 0..(level.size - 2)) {
        if (isIncrementing && level[i + 1] - level[i] !in 1..3)
            return false
        if (!isIncrementing && level[i + 1] - level[i] !in -3..-1 )
            return false
    }
    return true
}

fun puzzle2b(): Int {
    var sum = 0
    var current: Int

    for (line in file2) {
        current = checkValues(line.split(" ").map { it.toInt() })
        if (current == 0)
            continue
        else
            sum++
    }
    return sum
}

fun checkValues(line: List<Int>): Int {
    var nUpCurrent = 0
    var nDownCurrent = 0
    var nCorrectIntensity = 0
    val wrongIndex = mutableListOf(0, 0)

    for (i in 0..(line.size - 2)) {
        if (line[i + 1] > line[i])
            nUpCurrent++
        else if (line[i + 1] < line[i])
            nDownCurrent++
        if ((line[i + 1] - line[i]).absoluteValue in 1..3)
            nCorrectIntensity++
    }
    if ((nUpCurrent == line.size - 1 || nDownCurrent == line.size - 1) && nCorrectIntensity == line.size - 1)
        return 1
    if ((nUpCurrent + 1 == line.size - 1 || nDownCurrent + 1 == line.size) && nCorrectIntensity == line.size - 1)
        return 1
    if ((nUpCurrent == line.size - 1 || nDownCurrent == line.size) && nCorrectIntensity + 1 == line.size - 1)
        return 1
    if ((nUpCurrent + 2 <= line.size - 1 && nDownCurrent + 2 <= line.size - 1) || nCorrectIntensity + 2 <= line.size - 1)
        return 0
    for (i in 0..(line.size - 2)) {
        if (nUpCurrent > nDownCurrent) {
            if (line[i + 1] - line[i] !in 1..3)
                wrongIndex[0] = i + 1
        } else {
            if (line[i + 1] - line[i] !in -3..-1)
                wrongIndex[0] = i + 1
        }
        if ((line[i + 1] - line[i]).absoluteValue !in 1..3)
            wrongIndex[1] = i + 1
    }
    print("$line, $wrongIndex")
    if (wrongIndex[0] == wrongIndex[1]) {
        println(" correct")
        return 1
    } else {
        println(" incorrect")
        return 0
    }
}

fun puzzle2a(): Int {
    var sum = 0

    for (line in file2) {
        if (processLevel(line.split(" ").map { to -> to.toInt() } ))
            sum++
    }
    return sum
}