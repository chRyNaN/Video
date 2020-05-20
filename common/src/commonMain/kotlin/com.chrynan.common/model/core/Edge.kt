package com.chrynan.common.model.core

interface Edge<N : Node> {

    val cursor: Cursor
    val node: N
}