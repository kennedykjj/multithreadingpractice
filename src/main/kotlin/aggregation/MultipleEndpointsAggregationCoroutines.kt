package org.example.aggregation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MultipleEndpointsAggregationCoroutines {

    private val client = OkHttpClient()

    private fun fetchData(url: String): String {
        val request = Request.Builder().url(url).build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            return response.body?.string() ?: throw IOException("Empty response body")
        }
    }

    private fun aggregateData(urls: List<String>): List<String> = runBlocking {
        val results = mutableListOf<String>()
        val jobs = urls.map { url ->
            async(Dispatchers.IO) {
                try {
                    val data = fetchData(url)
                    synchronized(results) {
                        results.add(data)
                    }
                } catch (e: IOException) {
                    println("Error fetching data from $url: ${e.message}")
                }
            }
        }
        jobs.awaitAll()
        return@runBlocking results
    }

    fun runIt(numOfUrls: Int) {
        val baseUrl = "https://jsonplaceholder.typicode.com/posts/"
        val urls = mutableListOf<String>()

        for (i in 1..numOfUrls) {
            urls.add("$baseUrl$i")
        }

        val startTime = System.currentTimeMillis()

        val dataAggregator = MultipleEndpointsAggregationCoroutines()

        println("Aggregating data from multiple APIs:")
        val aggregatedData = dataAggregator.aggregateData(urls)

        val endTime = System.currentTimeMillis()

        val executionTime = endTime - startTime

        println("Execution Time: $executionTime ms")

    }
}