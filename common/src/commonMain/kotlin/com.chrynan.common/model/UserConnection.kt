package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

class UserConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<UserEdge> = emptyList(),
    override val nodes: List<User> = emptyList()
) : Connection<User, UserEdge>