package com.chrynan.video.common.mapper

interface Mapper<IN, OUT> {

    suspend fun map(model: IN): OUT
}