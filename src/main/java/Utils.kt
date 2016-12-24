package utils

inline fun solveAll(f: (String, Boolean) -> Any, vararg inputs: String) {
    inputs.forEach {
        println("Part 1")
        f(it, true)
        println("Part 2")
        f(it, false)
    }
}

fun <T> List<T>.window(size: Int, step: Int, wrap: Boolean = false): Sequence<List<T>> {
    require(size > 0)
    var i = 0

    return generateSequence {
        if (i >= this.size) return@generateSequence null

        val end = i + size
        val sub = subList(i, end.coerceAtMost(this.size)).apply { i += step }

        if (wrap && end >= this.size) {
            sub + subList(0, end % this.size)
        } else {
            sub
        }
    }
}