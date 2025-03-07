package org.example.aggregation

import okhttp3.OkHttpClient
import okhttp3.Request
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.io.IOException

class MultipleEndpointsAggregationReactor {

    private val client = OkHttpClient()

    private fun fetchData(url: String): Mono<String> {
        return Mono.fromCallable {
            val request = Request.Builder().url(url).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                response.body?.string() ?: throw IOException("Empty response body")
            }
        }.subscribeOn(Schedulers.boundedElastic())
    }

    private fun aggregateData(urls: List<String>): Flux<String> {
        return Flux.fromIterable(urls)
            .flatMap { url -> fetchData(url) }
    }

    fun runIt(numOfUrls: Int) {
        val baseUrl = "https://jsonplaceholder.typicode.com/posts/"
        val urls = mutableListOf<String>()

        for (i in 1..numOfUrls) {
            urls.add("$baseUrl$i")
        }

        val startTime = System.currentTimeMillis()

        println("Aggregating data from multiple APIs:")
        aggregateData(urls)
            .collectList()
            .block()

        val endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime

        println("Execution Time: $executionTime ms")
    }
}