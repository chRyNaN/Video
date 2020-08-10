package com.chrynan.video.common.validation.core

interface Validator<T, R> {

    operator fun invoke(input: T): ValidationResult<R>
}