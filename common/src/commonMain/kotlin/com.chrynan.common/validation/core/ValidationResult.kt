package com.chrynan.common.validation.core

sealed class ValidationResult {

    object Valid : ValidationResult()

    class Invalid(vararg val errors: ValidationError) : ValidationResult()
}

val ValidationResult.isValid
    get() = this is ValidationResult.Valid

val ValidationResult.isInvalid
    get() = this is ValidationResult.Invalid

operator fun ValidationResult.contains(error: ValidationError): Boolean =
    this is ValidationResult.Invalid && errors.contains(error)