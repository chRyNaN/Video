package com.chrynan.common.mapper

interface Mapper<IN, OUT> {

    suspend fun map(model: IN): OUT
}