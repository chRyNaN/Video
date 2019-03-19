package com.chrynan.common.model

interface Connection<N, E : Edge<N>> {

    val totalCount: Int
    val pageInfo: PageInfo
    val edges: List<E>
}