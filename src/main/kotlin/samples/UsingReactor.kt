package org.example.samples

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

class UsingReactor {

    fun runIt() {
        val tasks = listOf(
            "Task1", "Task2", "Task3", "Task4", "Task5",
            "Task6", "Task7", "Task8", "Task9", "Task10",
            "Task11", "Task12", "Task13", "Task14", "Task15",
            "Task16", "Task17", "Task18", "Task19", "Task20"
        )
        val startTime = System.currentTimeMillis()

        Flux.fromIterable(tasks)
            .flatMap { task ->
                Flux.just(task)
                    .map {
                        execute(task)
                    }
                    .subscribeOn(Schedulers.boundedElastic())
            }
            .doOnNext {
//                println("Result: $result")
            }
            // wait for it...
            .blockLast()

        val endTime = System.currentTimeMillis()
        println("Execution Time: ${endTime - startTime} ms")
    }

    private fun execute(task: String): String {
        println("Executing task $task on thread ${Thread.currentThread().name}")
        Thread.sleep(500)
        return "$task done"
    }
}