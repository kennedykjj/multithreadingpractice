package org.example.samples

import java.util.concurrent.Executors

class UsingConcurrent {
    fun runIt() {
        val tasks = listOf(
            "Task1", "Task2", "Task3", "Task4", "Task5",
            "Task6", "Task7", "Task8", "Task9", "Task10",
            "Task11", "Task12", "Task13", "Task14", "Task15",
            "Task16", "Task17", "Task18", "Task19", "Task20"
        )

        val executor = Executors.newFixedThreadPool(10)

        tasks.forEach { task ->
            executor.submit {
                val result = execute(task)
                println("Result: $result")
            }
        }
        executor.shutdown()
        while (!executor.isTerminated) {
            // wait for it.....
        }


    }
    private fun execute(task: String): String {
        println("Executing task $task on thread ${Thread.currentThread().name}")
        Thread.sleep(500)
        return "$task done"
    }
}