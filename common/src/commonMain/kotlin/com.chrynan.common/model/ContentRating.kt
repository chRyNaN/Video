package com.chrynan.common.model

data class ContentRating(
    val minAge: Int,
    val discretionAdvised: Boolean,
    val standard: String? = null,
    val issuer: String? = null,
    val type: ContentRatingType
)