package aggregation

import org.example.aggregation.MultipleEndpointsAggregationReactor
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class MultipleEndpointsAggregationReactorTest{
    @Test
    fun testRunIt() {
        val aggregationReactor = MultipleEndpointsAggregationReactor()
        assertDoesNotThrow {
            aggregationReactor.runIt(50)
        }
    }
}