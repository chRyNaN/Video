package com.chrynan.common.model

interface Edge<T> {

    val cursor: Cursor
    val node: T
}