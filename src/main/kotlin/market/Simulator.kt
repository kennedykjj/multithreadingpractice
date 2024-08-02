package org.example.market

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.example.market.domain.Agent
import org.example.market.domain.MarketEvent
import org.example.market.domain.TradingStrategy
import org.example.market.domain.Transaction
import org.example.market.domain.TransactionType
import java.lang.management.ManagementFactory
import kotlin.random.Random

class Simulator {
    private val market = Market()
    private val agents = List(10) {
        Agent(it, "Agent $it", 1000.0, TradingStrategy.values().random())
    }

    fun simulate(numTransactions: Int) = runBlocking {
        val threadMXBean = ManagementFactory.getThreadMXBean()
        val initialThreadCount = threadMXBean.threadCount


        val jobs = (1..numTransactions).map {
            async(Dispatchers.Default) {
// thinking in a real case, IO would be more recommend because of all the databases and probably shared resources
// in my tests default was 20% faster in comparison of IO dispatcher for 1.000.000 transactions
                val agent = agents.random()
                val asset = market.getAssets().random()
                val type = if (Random.nextBoolean()) TransactionType.BUY else TransactionType.SELL
                val amount = Random.nextDouble(1.0, 100.0)
                val price = market.getPrice(asset.id)
                val transaction = Transaction(agent, asset, type, amount, price)
                market.executeTransaction(transaction)
            }
        }
        jobs.awaitAll()

        val finalThreadCount = threadMXBean.threadCount
        println("Threads initiated during simulation: $initialThreadCount and finished with: $finalThreadCount")
    }

    fun applyRandomMarketEvent() {
        val event = MarketEvent("News Impact", Random.nextDouble(-0.05, 0.05))
        market.applyMarketEvent(event)
    }

    fun printMarketStatus() {
        println("Market Status:")
        market.getAssets().forEach { asset ->
            println("Asset: ${asset.name}, Price: ${asset.price}")
        }
        println("Total Transactions: ${market.getTransactions().size}")
    }
}