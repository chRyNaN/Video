package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.validation.core.ValidationResult
import com.chrynan.common.validation.core.contains
import com.chrynan.common.validation.error.EmptyUriStringError
import com.chrynan.common.validation.error.InvalidUriStringError
import com.chrynan.common.validation.validator.UriStringValidator
import com.chrynan.video.R
import com.chrynan.video.resources.ResourceAccessor
import com.chrynan.video.ui.view.NewServiceProviderView
import javax.inject.Inject

class NewServiceProviderPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    resourceAccessor: ResourceAccessor,
    private val view: NewServiceProviderView,
    private val validator: UriStringValidator
) : BasePresenter(dispatchers),
    ResourceAccessor by resourceAccessor {

    private val errorEmptyText by string(R.string.new_service_provider_error_empty)
    private val errorInvalidText by string(R.string.new_service_provider_error_invalid)
    private val errorNoApiText by string(R.string.new_service_provider_error_no_api)

    fun handleProviderUriStringChange(providerUri: String?) {
        val result = validator(input = providerUri)

        when {
            result is ValidationResult.Valid -> view.showProviderUriValid()
            result.contains(EmptyUriStringError) -> view.showProviderUriInvalid(errorEmptyText)
            result.contains(InvalidUriStringError) -> view.showProviderUriInvalid(errorInvalidText)
        }
    }
}