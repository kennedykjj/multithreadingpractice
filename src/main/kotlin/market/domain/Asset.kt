package org.example.market.domain

data class Asset(val id: Int, val name: String, val type: AssetType, var price: Double)

enum class AssetType {
    STOCK, BOND, COMMODITY
}