package samples

import org.example.samples.UsingConcurrent
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class UsingConcurrentTest {

    @Test
    fun testRunIt() {
        val usingConcurrent = UsingConcurrent()
        assertDoesNotThrow {
            usingConcurrent.runIt()
        }
    }
}