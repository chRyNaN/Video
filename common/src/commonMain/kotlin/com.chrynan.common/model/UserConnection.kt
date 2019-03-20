package com.chrynan.common.model

class UserConnection(
    override val totalCount: Int,
    override val pageInfo: PageInfo,
    override val edges: List<UserEdge>
) : Connection<User, UserEdge>