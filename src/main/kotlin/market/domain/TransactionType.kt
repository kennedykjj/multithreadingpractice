package org.example.market.domain

data class Transaction(val agent: Agent, val asset: Asset, val type: TransactionType, val amount: Double, val price: Double)

enum class TransactionType {
    BUY, SELL
}