package org.example.market

import org.example.market.domain.Asset
import org.example.market.domain.AssetType
import org.example.market.domain.MarketEvent
import org.example.market.domain.Transaction
import org.example.market.domain.TransactionType
import java.util.concurrent.ConcurrentLinkedQueue

class Market {

    private val transactions = ConcurrentLinkedQueue<Transaction>()
    private val assets = mutableMapOf<Int, Asset>()

    init {
        // Initializing some assets
        assets[1] = Asset(1, "Stock A", AssetType.STOCK, 100.0)
        assets[2] = Asset(2, "Bond B", AssetType.BOND, 200.0)
        assets[3] = Asset(3, "Commodity C", AssetType.COMMODITY, 50.0)
    }

    fun executeTransaction(transaction: Transaction) {
        transactions.add(transaction)
        updatePrice(transaction)
    }

    private fun updatePrice(transaction: Transaction) {
        val asset = transaction.asset
        val newPrice = if (transaction.type == TransactionType.BUY) {
            asset.price + transaction.amount * 0.01
        } else {
            asset.price - transaction.amount * 0.01
        }
        asset.price = newPrice
    }

    fun applyMarketEvent(event: MarketEvent) {
        assets.values.forEach { asset ->
            asset.price += asset.price * event.impact
        }
    }

    fun getPrice(assetId: Int): Double {
        return assets[assetId]?.price ?: 0.0
    }

    fun getTransactions(): List<Transaction> {
        return transactions.toList()
    }

    fun getAssets(): List<Asset> {
        return assets.values.toList()
    }
}