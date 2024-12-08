import java.io.File

val file3: String = File("src/resources/input3.txt").readText()

fun puzzle3a(): Int {
    var sum = 0
    var str: String
    var found: String
    var digits: List<String>
    var left: Int
    var rigth: Int

    for (i in file3.indices) {
        str = file3.substring(i)
        if (str.startsWith("mul(")) {
            found = str.substring(startIndex = 4, endIndex = str.indexOf(')'))
            if (found.contains(',')) {
                digits = found.split(",", ignoreCase = true, limit = 2)
                if (digits[0].toIntOrNull() != null && digits[1].toIntOrNull() != null) {
                    left = digits[0].toInt()
                    rigth = digits[1].toInt()
                    sum += left * rigth
                    println(found)
                }
            }
        }
    }
    return sum
}

fun puzzle3b(): Int {
    var enable = true
    var sum = 0
    var str: String
    var found: String
    var digits: List<String>
    var left: Int
    var rigth: Int

    for (i in file3.indices) {
        str = file3.substring(i)
        if (str.startsWith("don't()")) {
            enable = false
        }
        if (str.startsWith("do()")) {
            enable = true
        }
        if (enable) {
            if (str.startsWith("mul(")) {
                found = str.substring(startIndex = 4, endIndex = str.indexOf(')'))
                if (found.contains(',')) {
                    digits = found.split(",", ignoreCase = true, limit = 2)
                    if (digits[0].toIntOrNull() != null && digits[1].toIntOrNull() != null) {
                        left = digits[0].toInt()
                        rigth = digits[1].toInt()
                        sum += left * rigth
                        println(found)
                    }
                }
            }
        }
    }
    return sum
}