package com.chrynan.common.model

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node

data class Rating(
    override val id: ID,
    val created: String,
    val lastUpdated: String,
    val ratingType: RatingType
) : Node