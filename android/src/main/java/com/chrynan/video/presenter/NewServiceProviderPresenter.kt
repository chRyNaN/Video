package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.ServiceProvider
import com.chrynan.common.model.core.UriString
import com.chrynan.common.repository.LoginInfoRepository
import com.chrynan.common.repository.ServiceProviderRepository
import com.chrynan.common.validation.core.ValidationResult
import com.chrynan.common.validation.core.contains
import com.chrynan.common.validation.error.EmptyUriStringError
import com.chrynan.common.validation.error.InvalidUriStringError
import com.chrynan.common.validation.validator.UriStringValidator
import com.chrynan.logger.Logger
import com.chrynan.video.R
import com.chrynan.video.resources.ResourceAccessor
import com.chrynan.video.ui.view.NewServiceProviderView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewServiceProviderPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    resourceAccessor: ResourceAccessor,
    private val view: NewServiceProviderView,
    private val validator: UriStringValidator,
    private val serviceProviderRepository: ServiceProviderRepository,
    private val loginInfoRepository: LoginInfoRepository
) : BasePresenter(dispatchers),
    ResourceAccessor by resourceAccessor {

    private val errorEmptyText by string(R.string.new_service_provider_error_empty)
    private val errorInvalidText by string(R.string.new_service_provider_error_invalid)
    private val errorNoApiText by string(R.string.new_service_provider_error_no_api)

    fun handleProviderUriStringChange(providerUri: String?) {
        val result = validator(input = providerUri)

        updateViewFromValidationResult(result)
    }

    fun handleProviderUriTest(providerUri: UriString?) {
        val result = validator(input = providerUri)

        updateViewFromValidationResult(result)

        if (result is ValidationResult.Valid) {
            val errorHandler = CoroutineExceptionHandler { _, throwable ->
                Logger.logError(throwable = throwable, message = "Error Saving Service Provider.")
            }

            launch(errorHandler) {
                val response =
                    loginInfoRepository.getLoginInfoByProviderUri(providerUri = result.value)

                if (response != null) {
                    val model = ServiceProvider(
                        providerUri = response.provider.uri,
                        apiVersion = response.apiVersion,
                        name = response.provider.name,
                        token = null // TODO update to retrieve Token
                    )

                    serviceProviderRepository.insert(model)
                } else {
                    withContext(dispatchers.main) { view.showProviderUriInvalid(errorNoApiText) }
                }
            }
        }
    }

    private fun <T> updateViewFromValidationResult(result: ValidationResult<T>) {
        when {
            result is ValidationResult.Valid -> view.showProviderUriValid()
            result.contains(EmptyUriStringError) -> view.showProviderUriInvalid(errorEmptyText)
            result.contains(InvalidUriStringError) -> view.showProviderUriInvalid(errorInvalidText)
        }
    }
}