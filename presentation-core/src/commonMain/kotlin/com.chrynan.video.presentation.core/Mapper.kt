package com.chrynan.video.presentation.core

interface Mapper<IN, OUT> {

    suspend fun map(model: IN): OUT
}