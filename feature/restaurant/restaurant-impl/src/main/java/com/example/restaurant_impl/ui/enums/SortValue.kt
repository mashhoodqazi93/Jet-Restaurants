package com.example.restaurant_impl.ui.enums

enum class SortValue(private val value: String) {
    STATUS("status"),
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