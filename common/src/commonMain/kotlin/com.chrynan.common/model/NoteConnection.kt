package com.chrynan.common.model

import com.chrynan.common.model.core.Connection
import com.chrynan.common.model.core.PageInfo

data class NoteConnection(
    override val totalCount: Int = 0,
    override val pageInfo: PageInfo = PageInfo(),
    override val edges: List<NoteEdge> = emptyList(),
    override val nodes: List<Note> = emptyList()
) : Connection<Note, NoteEdge>