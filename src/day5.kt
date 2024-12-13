import java.io.File

var file5 = File("src/resources/input5.txt").readLines()

fun puzzle5b(): Int {
    var check: Boolean
    var sum = 0
    val rules = getRules()

    for (i in file5.indexOf("") + 1..<file5.size) {
        check = true
        val line = file5[i].split(",").map { it.toInt() }
        for (j in 0..<line.size) {
            if (line[j] !in rules.keys)
                continue
            var next: List<Int> = nextDigits(line[j], line)
            var presentRules: List<Int> = presentRulesDigits(rules.getValue(line[j]), line)
            if (!next.containsAll(presentRules))
                check = false
            if (!check)
                break
        }
        if (!check)
            sum += fixUpdate(rules, line)
    }
    return sum
}

fun fixUpdate(rules: Map<Int, MutableList<Int>>, line: List<Int>): Int {
    val arr = Array (line.size) {0}
    println("fix: $line")

    for (digit in line) {
        if (digit !in rules.keys)
        {
            arr[0] = digit
            continue
        }
        val presentRules = presentRulesDigits(rules.getValue(digit), line)
        if (presentRules.size == 0)
            arr[0] = digit
        else
            arr[presentRules.size] = digit
    }
    print("Fixed: ")
    println(arr.toList())
    return (arr[line.size/2])
}

fun getRules(): Map<Int, MutableList<Int>> {
    val rules: MutableMap<Int, MutableList<Int>> = mutableMapOf()

    for (i in 0..<file5.indexOf("")) {
        val line = file5[i].split("|", limit = 2).map { it.toInt() }
        if (line[0] !in rules.keys)
            rules[line[0]] = mutableListOf(line[1])
        else
            rules.getValue(line[0]).add(line[1])
    }
    return rules
}

fun puzzle5a(): Int {
    var check: Boolean
    var sum = 0
    var rules = getRules()

    for (i in file5.indexOf("") + 1..<file5.size) {
        check = true
        val line = file5[i].split(",").map { it.toInt() }
        for (j in 0..<line.size) {
            if (line[j] !in rules.keys)
                continue
            var next: List<Int> = nextDigits(line[j], line)
            var presentRules: List<Int> = presentRulesDigits(rules.getValue(line[j]), line)
            if (!next.containsAll(presentRules))
                check = false
            if (!check)
                break
        }
        if (check)
            sum += line[line.size/2]
    }
    return sum
}

fun presentRulesDigits(rules: List<Int>, line: List<Int>): List<Int> {
    val presentRules: MutableList<Int> = mutableListOf()

    for (rule in rules) {
        if (line.contains(rule))
            presentRules.add(rule)
    }
    return presentRules
}

fun nextDigits(digit: Int, line: List<Int>): List<Int> {
    val next: MutableList<Int> = mutableListOf()

    for (i in line.indexOf(digit) + 1..<line.size)
        next.add(line[i])
    return next
}
