package com.chrynan.video.di.module

import com.chrynan.common.validation.validator.UriStringValidator
import com.chrynan.video.validator.UriStringValidatorSource
import dagger.Binds
import dagger.Module

@Module
internal abstract class ValidatorModule {

    @Binds
    abstract fun bindUriStringValidator(validator: UriStringValidatorSource): UriStringValidator
}