package org.example

import org.example.aggregation.MultipleEndpointsAggregationCoroutines
import org.example.samples.UsingConcurrent
import org.example.samples.UsingCoroutines
import org.example.samples.UsingReactor

fun main() {
    val usingConcurrent = UsingConcurrent()
    val usingReactor = UsingReactor()
    val usingCoroutines = UsingCoroutines()
    val aggregationCoroutines = MultipleEndpointsAggregationCoroutines()
    val aggregationReactor = MultipleEndpointsAggregationCoroutines()


    println("Executing tasks with concurrent:").also { usingConcurrent.runIt() }

    println("Executing tasks with reactor:").also { usingReactor.runIt() }

    println("Executing tasks with coroutines:").also { usingCoroutines.runIt() }

    println("Executing tasks data aggregation task with coroutines:").also { aggregationCoroutines.runIt(100) } // average 4.5s

    println("Executing tasks data aggregation taskwith reactor:").also { aggregationReactor.runIt(100) } // average 2.3s

}