package com.chrynan.common.utils

import com.chrynan.common.mapper.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R> Flow<Collection<T>>.mapEachItemWith(mapper: Mapper<T, R>): Flow<List<R>> =
    map { list -> list.map { mapper.map(it) } }