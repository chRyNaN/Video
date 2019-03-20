package com.chrynan.common.model

data class Rating(
    val id: ID,
    val created: String,
    val lastUpdated: String,
    val ratingType: RatingType
)