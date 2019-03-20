package com.chrynan.common.model

data class UserEdge(
    override val cursor: Cursor,
    override val node: User
) : Edge<User>