import java.io.File

var file6 = File("src/resources/ej.txt").readLines()

fun puzzle6a(): Int{
    var sum = 0
    var x: Int = 0
    var y: Int = 0
    val width = 10
    val height = 10
    val matrix = Array (height) {Array (width) {0} }

    for (i in file6.indices) {
        for (j in file6[i].indices) {
            matrix[i][j] = when (file6[i][j]) {
                '#' -> 1
                '^' -> 2
                else -> 0
            }
            if (matrix[i][j] == 2) {
                y = i
                x = j
            }
        }
    }
    while (true) {
        y = moveUp(matrix, y, x)
        if (y == -1)
            break
        matrix.forEach { println(it.toList()) }
        println()
        x = moveRight(matrix, y, x)
        if (x == -1)
            break
        matrix.forEach { println(it.toList()) }
        println()
        y = moveDown(matrix, y, x)
        if (y == -1)
            break
        matrix.forEach { println(it.toList()) }
        println()
        x = moveLeft(matrix, y, x)
        if (x == -1)
            break
        matrix.forEach { println(it.toList()) }
        println()
    }
    matrix.forEach { println(it.toList()) }
    for (i in matrix.indices) {
        for (j in matrix[i].indices)
            if (matrix[i][j] == 3)
                sum++
    }
    return sum
}

fun moveDown(matrix: Array<Array<Int>>, y: Int, x: Int): Int {
    for (i in y..<matrix.size) {
        if (matrix[i][x] == 1)
            return i - 1
        matrix[i][x] = 3
    }
    return -1
}

fun moveLeft(matrix: Array<Array<Int>>, y: Int, x: Int): Int {
    for (j in x.downTo(0)) {
        if (matrix[y][j] == 1)
            return j + 1
        matrix[y][j] = 3
    }
    return -1
}

fun moveRight(matrix: Array<Array<Int>>, y: Int, x: Int): Int {
    for (j in x..<matrix[0].size) {
        if (matrix[y][j] == 1)
            return j - 1
        matrix[y][j] = 3
    }
    return -1
}

fun moveUp(matrix: Array<Array<Int>>, y: Int, x: Int): Int {
    for (i in y.downTo(0)) {
       if (matrix[i][x] == 1)
           return i + 1
       matrix[i][x] = 3
    }
    return -1
}


fun puzzle6b():Int {
    var sum = 0
    val width = 10
    val height = 10
    val matrix = Array (height) {Array (width) {0} }

    for (i in file6.indices) {
        for (j in file6[i].indices) {
            matrix[i][j] = when (file6[i][j]) {
                '#' -> 1
                '^' -> 2
                else -> 0
            }
        }
    }
    var tile: Int
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            tile = matrix[i][j]
            if (tile == 1) {
                sum += findLoops(matrix, i + 1, j)
            }
        }
    }
    return sum
}

fun findLoops(matrix: Array<Array<Int>>, i: Int, j: Int): Int {
    var sum = 0
    val corners: MutableList<Pair<Int, Int>> = mutableListOf()

    matrix.forEach { println(it.toList()) }
    println()
    corners.add(searchRight(matrix, i, j))
    if (corners[0].first != -1) {
        matrix[corners[0].first][corners[0].second] = 3
    }
    matrix.forEach { println(it.toList()) }
    return 0
}

fun searchRight(matrix: Array<Array<Int>>, i: Int, j: Int): Pair<Int, Int> {
    var x = 0
    var searchTile = matrix[i][j + x]

    while (searchTile != 1 && x + j < matrix[j].size) {
        x++
        searchTile = matrix[i][j + x]
    }
    if (searchTile == 1)
        return Pair(i , x)
    return Pair(-1, -1)
}
