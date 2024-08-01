package samples

import org.example.samples.UsingCoroutines
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class UsingCoroutinesTest {
    @Test
    fun testRunIt() {
        val usingCoroutines = UsingCoroutines()
        assertDoesNotThrow {
            usingCoroutines.runIt()
        }
    }
}