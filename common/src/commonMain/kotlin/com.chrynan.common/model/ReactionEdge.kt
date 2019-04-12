package com.chrynan.common.model

data class ReactionEdge(
    override val cursor: Cursor,
    override val node: Reaction
) : Edge<Reaction>