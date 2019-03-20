package com.chrynan.common.model

data class NoteEdge(
    override val cursor: Cursor,
    override val node: Note
) : Edge<Note>