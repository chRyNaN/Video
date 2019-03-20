package com.chrynan.common.model

data class RatingEdge(
    override val cursor: Cursor,
    override val node: Rating
) : Edge<Rating>