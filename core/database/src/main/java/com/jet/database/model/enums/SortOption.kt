package com.jet.database.model.enums

enum class SortOption(private val value: String) {
    NONE("none"),
    BEST_MATCH("bestMatch"),
    NEWEST("newest"),
    RATING_AVERAGE("ratingAverage"),
    DISTANCE("distance"),
    POPULARITY("popularity"),
    PRODUCT_PRICE_AVERAGE("averageProductPrice"),
    DELIVERY_COST("deliveryCosts"),
    MIN_COST("minCost");

    override fun toString(): String {
        return value
    }
}