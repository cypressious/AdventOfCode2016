package d4

import utils.Day
import kotlin.comparisons.compareByDescending
import kotlin.comparisons.thenBy

val testInput = """
aaaaa-bbb-z-y-x-123[abxyz]
a-b-c-d-e-f-g-h-987[abcde]
not-a-real-room-404[oarel]
totally-real-room-200[decoy]""".trimMargin()

fun main(args: Array<String>) = Day4()

object Day4 : Day({
    +::testInput
    +res("4.txt")
}) {
    override fun solve(input: List<String>, part1: Boolean): Any? {
        val validRooms = validRooms(input)

        return when {
            part1 -> validRooms.sumBy(Room::id)
            else -> {
                val index = validRooms
                        .map { (_, id, _, text) ->
                            text.map { it.toCharArray().map { it.shiftBy(id) }.joinToString("") }
                        }
                        .indexOf(listOf("northpole", "object", "storage"))
                validRooms.elementAtOrNull(index)?.id
            }
        }
    }


    val COMPARATOR =
            compareByDescending<Map.Entry<Char, List<Char>>> { it.value.size }
                    .thenBy { it.key }

    data class Room(val letters: String, val id: Int, val checksum: String, val text: List<String>)


    fun Char.shiftBy(n: Int): Char {
        val mod = n % 26
        val shiftedForward = this + mod
        return if (shiftedForward > 'z') {
            shiftedForward - 26
        } else {
            shiftedForward
        }
    }

    private fun validRooms(input: List<String>): List<Room> = input
            .map { line ->
                val parts = line.takeWhile { it != '[' }.split("-")

                val id = parts.last().toInt()

                val text = parts.dropLast(1)
                val letters = text
                        .flatMap { it.toCharArray().toList() }
                        .groupBy { it }
                        .entries
                        .sortedWith(COMPARATOR)
                        .take(5)
                        .joinToString(separator = "") { it.key.toString() }

                val checksum = line.takeLast(7).substring(1, 6)

                Room(letters, id, checksum, text)
            }
            .filter { (letters, _, checksum) -> letters == checksum }

}