package aggregation

import org.example.aggregation.MultipleEndpointsAggregationCoroutines
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MultipleEndpointsAggregationCoroutinesTest {

    @Test
    fun testRunIt() {
        val aggregationCoroutines = MultipleEndpointsAggregationCoroutines()
        assertDoesNotThrow {
            aggregationCoroutines.runIt(50)
        }
    }
}