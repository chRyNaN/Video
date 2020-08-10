package com.chrynan.video.common.utils

import com.chrynan.video.common.mapper.Mapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

fun <T> flowFrom(builder: suspend () -> T): Flow<T> = flow {
    emit(builder())
}

fun <T, R> Flow<Collection<T>>.mapEachItemWith(mapper: Mapper<T, R>): Flow<List<R>> =
    map { list -> list.map { mapper.map(it) } }

inline fun <reified R> Flow<Collection<*>>.filterEachItemIsInstance() = map {
    it.filterIsInstance<R>()
}

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

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SharedFlow<T>(
    originalFlow: Flow<T>,
    coroutineScope: CoroutineScope
) {

    private val channel = originalFlow.conflate().broadcastIn(coroutineScope)

    fun openSubscription(): Flow<T> = channel.asFlow()
}

fun <T> Flow<T>.shareIn(coroutineScope: CoroutineScope): SharedFlow<T> =
    SharedFlow(
        originalFlow = this,
        coroutineScope = coroutineScope
    )