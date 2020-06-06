package com.chrynan.common.validation.core

interface Validator<T> {

    operator fun invoke(input: T): ValidationResult
}