package com.chrynan.common.repository.core

import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.Node

interface Repository<N : Node> {

    suspend fun getById(id: ID): N

    suspend fun delete(id: ID)
}