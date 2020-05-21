package com.chrynan.video.mapper

interface Mapper<IN, OUT> {

    suspend fun map(model: IN): OUT
}