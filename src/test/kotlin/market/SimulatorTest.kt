package market

import org.example.market.Simulator
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class SimulatorTest {
    @Test
    fun testSimulate() {
        val simulator = Simulator()
        val startTime = System.currentTimeMillis()

        assertDoesNotThrow {
            simulator.simulate(1000000)
            simulator.applyRandomMarketEvent()
            simulator.printMarketStatus()
        }

        val endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime

        println("Execution Time for MarketSimulator: $executionTime ms")
    }
}