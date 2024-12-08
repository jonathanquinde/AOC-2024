import java.io.File

val file4 = File("src/resources/input4.txt").readLines()

fun check(matrix: Array<Array<Char>>, yRange: IntRange, xRange: IntRange): Int {
    var p = 0
    var i: Int = yRange.first
    var j: Int = xRange.first
    val word: CharArray = charArrayOf('0', '0', '0', '0')

    while (p in 0..3) {
        word[p] = matrix[i][j]
        p++
        if (yRange.first > yRange.last)
            i--
        else if (yRange.first < yRange.last)
            i++
        if (xRange.first > xRange.last)
           j--
        else if (xRange.first < xRange.last)
            j++
    }
    if (word[0] == 'X' && word[1] == 'M' && word[2] == 'A' && word[3] == 'S')
        return 1
    return 0
}

fun checkB(upLeft: Char, upRight: Char, downLeft: Char, downRight: Char): Int {
    var numM = 0
    var numS = 0
    val arr = charArrayOf(upLeft, upRight, downLeft, downRight)

    println("$upLeft $upRight\n$downLeft $downRight")
    for (char in arr) {
       if (char == 'M')
           numM++
       else if (char == 'S')
           numS++
    }
    if (numM == 2 && numS == 2 && upLeft != downRight) {
        return 1
    } else {
        return 0
    }
}

fun puzzle4a(): Int {
    var sum = 0
    val width = 140
    val height = 140
    val checkTopRange = 3..<height
    val checkDownRange = 0..(height - 4)
    val checkLeftRange = 3..<width
    val checkRightRange = 0..(width - 4)
    val matrix = Array (height) {Array (width) { '0' } }

    for ((i, line) in file4.withIndex()) {
        for ((j, char) in line.withIndex()) {
            matrix[i][j] = char
        }
    }
    for (i in matrix.indices) {
        for (j in matrix.indices) {
            if (matrix[i][j] == 'X') {
                if (i in checkTopRange)
                    sum += check(matrix, i..(i - 3), j..j)
                if (i in checkDownRange)
                    sum += check(matrix, i..(i + 3), j..j)
                if (j in checkRightRange)
                    sum += check(matrix, i..i,j..(j + 3))
                if (j in checkLeftRange)
                    sum += check(matrix, i..i,j..(j - 3))
                if (i in checkTopRange && j in checkLeftRange)
                    sum += check(matrix, i..(i - 3), j..(j - 3))
                if (i in checkTopRange && j in checkRightRange)
                    sum += check(matrix, i..(i - 3), j..(j + 3))
                if (i in checkDownRange && j in checkLeftRange)
                    sum += check(matrix, i..(i + 3), j..(j - 3))
                if (i in checkDownRange && j in checkRightRange)
                    sum += check(matrix, i..(i + 3), j..(j + 3))
            }
        }
    }
    return sum
}

fun puzzle4b(): Int {
    var sum = 0
    val width = 140
    val height = 140
    val matrix = Array (height) {Array (width) { '0' } }

    for ((i, line) in file4.withIndex()) {
        for ((j, char) in line.withIndex()) {
            matrix[i][j] = char
        }
    }
    for (i in 1..height - 2) {
        for (j in 1..width - 2) {
            if (matrix[i][j] == 'A') {
                sum += checkB(matrix[i - 1][j - 1], matrix[i - 1][j + 1], matrix[i + 1][j - 1], matrix[i + 1][j + 1])
            }
        }
    }
    return sum
}