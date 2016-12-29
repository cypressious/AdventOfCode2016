package utils

import org.junit.Test
import java.security.MessageDigest
import kotlin.test.assertEquals

class UtilsKtTest {
    @Test
    fun window() {
        val range = 1..3
        assertEquals("[1,2,3],[2,3],[3]", range.windowString(3, 1, false))
        assertEquals("[1,2,3],[2,3,1],[3,1,2]", range.windowString(3, 1, true))
        assertEquals("[1,2],[3]", range.windowString(2, 2, false))
        assertEquals("[1,2],[3,1]", range.windowString(2, 2, true))
    }

    private fun IntRange.windowString(size: Int, step: Int, wrap: Boolean) = toList().window(size, step, wrap)
            .map { it.joinToString(",", "[", "]") }
            .joinToString(",")

    @Test
    fun testMd5() {
        assertEquals("000001", MessageDigest.getInstance("MD5").md5("abc3231929").substring(0, 6))
    }
}