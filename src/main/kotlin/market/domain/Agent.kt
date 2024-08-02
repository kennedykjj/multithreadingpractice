package org.example.market.domain

data class Agent(val id: Int, val name: String, val balance: Double, val strategy: TradingStrategy)

enum class TradingStrategy {
    DAY_TRADING, SWING_TRADING, LONG_TERM
}