package org.example

fun main() {
    val usingConcurrent = UsingConcurrent()
    val usingReactor = UsingReactor()
    val usingCoroutines = UsingCoroutines()

    println("Executing tasks with concurrent:")
    usingConcurrent.runIt()

    println("\nExecuting tasks with reactor:")
    usingReactor.runIt()

    println("\nExecuting tasks with coroutines:")
    usingCoroutines.runIt()
}