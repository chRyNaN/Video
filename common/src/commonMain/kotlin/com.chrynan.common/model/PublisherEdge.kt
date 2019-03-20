package com.chrynan.common.model

data class PublisherEdge(
    override val cursor: Cursor,
    override val node: Publisher
) : Edge<Publisher>