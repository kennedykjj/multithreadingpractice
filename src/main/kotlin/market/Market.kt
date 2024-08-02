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
        assets[1] = Asset(1, "Stock A", AssetType.STOCK, 100.0)
        assets[2] = Asset(2, "Bond A", AssetType.BOND, 200.0)
        assets[3] = Asset(3, "Commodity A", AssetType.COMMODITY, 50.0)
        assets[4] = Asset(4, "Stock B", AssetType.STOCK, 100.0)
        assets[5] = Asset(5, "Bond B", AssetType.BOND, 200.0)
        assets[6] = Asset(6, "Commodity B", AssetType.COMMODITY, 50.0)
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
        asset.price = maxOf(newPrice, 0.0)
    }

    fun applyMarketEvent(event: MarketEvent) {
        assets.values.forEach { asset ->
            asset.price = maxOf(asset.price + asset.price * event.impact, 0.0)        }
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