package utils

inline fun solveAll(f: (String, Boolean) -> Any, vararg inputs: String) {
    inputs.forEach {
        println("Part 1")
        f(it, true)
        println("Part 2")
        f(it, false)
    }
}