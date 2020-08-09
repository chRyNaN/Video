package com.chrynan.video.presentation.mapper

interface Mapper<IN, OUT> {

    suspend fun map(model: IN): OUT
}