package org.example.samples

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class UsingCoroutines {

    fun runIt() {
        val tasks = listOf(
            "Task1", "Task2", "Task3", "Task4", "Task5",
            "Task6", "Task7", "Task8", "Task9", "Task10",
            "Task11", "Task12", "Task13", "Task14", "Task15",
            "Task16", "Task17", "Task18", "Task19", "Task20"
        )
        runBlocking {

            val startTime = System.currentTimeMillis()

            val jobs = tasks.map { task ->
                launch(Dispatchers.Default) {
                     execute(task)
//                    println("Result: $result")
                }
            }

            // wait for it...
            jobs.forEach { it.join() }

            val endTime = System.currentTimeMillis()
            println("Execution Time: ${endTime - startTime} ms")
        }
    }

    private fun execute(task: String): String {
        println("Executing task $task on thread ${Thread.currentThread().name}")
        Thread.sleep(Random.nextLong(1000, 3000))
        return "$task done"
    }
}