package com.chrynan.video.common.validation.core

sealed class ValidationResult<T> {

    class Valid<T>(val value: T) : ValidationResult<T>()

    class Invalid<T>(vararg val errors: ValidationError) : ValidationResult<T>()
}

val <T> ValidationResult<T>.isValid
    get() = this is ValidationResult.Valid

val <T> ValidationResult<T>.isInvalid
    get() = this is ValidationResult.Invalid

operator fun <T> ValidationResult<T>.contains(error: ValidationError): Boolean =
    this is ValidationResult.Invalid && errors.contains(error)