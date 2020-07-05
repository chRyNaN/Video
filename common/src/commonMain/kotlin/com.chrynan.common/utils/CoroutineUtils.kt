package com.chrynan.common.utils

import com.chrynan.common.mapper.Mapper
import kotlinx.coroutines.flow.*

fun <T> flowFrom(builder: suspend () -> T): Flow<T> = flow {
    emit(builder())
}

fun <T, R> Flow<Collection<T>>.mapEachItemWith(mapper: Mapper<T, R>): Flow<List<R>> =
    map { list -> list.map { mapper.map(it) } }

fun <T> Flow<T>.onFirstEmit(action: suspend (T) -> Unit): Flow<T> {
    var firstEmit = true

    return this.onEach {
        if (firstEmit) {
            action(it)
        }

        firstEmit = false
    }
}

fun <T> Flow<T>.onError(action: suspend (Throwable) -> Unit): Flow<T> = catch {
    action(it)
    throw it
}

fun <T> Flow<T>.filterUntilFirstChange(): Flow<T> {
    var firstCharEntered = false
    var firstEmittedItem: T? = null

    return this.onFirstEmit { firstEmittedItem = it }
        .filter {
            if (firstCharEntered) {
                true
            } else {
                firstCharEntered = it != firstEmittedItem
                firstCharEntered
            }
        }
}

fun <T> Flow<T>.firstAsFlow(): Flow<T> = flow { emit(first()) }