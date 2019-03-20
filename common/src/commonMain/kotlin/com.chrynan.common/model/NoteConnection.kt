package com.chrynan.common.model

data class NoteConnection(
    override val totalCount: Int,
    override val pageInfo: PageInfo,
    override val edges: List<NoteEdge>
) : Connection<Note, NoteEdge>