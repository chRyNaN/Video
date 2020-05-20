package com.chrynan.presentation.mapper

interface Mapper<IN, OUT> {

    suspend fun map(model: IN): OUT
}