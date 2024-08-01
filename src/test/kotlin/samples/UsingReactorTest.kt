package samples

import org.example.samples.UsingReactor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class UsingReactorTest {

    @Test
    fun testRunIt() {
        val usingReactor = UsingReactor()
        assertDoesNotThrow {
            usingReactor.runIt()
        }
    }
}