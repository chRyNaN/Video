package com.chrynan.video.validator

import android.util.Patterns
import com.chrynan.common.model.core.UriString
import com.chrynan.common.validation.core.ValidationResult
import com.chrynan.common.validation.error.EmptyUriStringError
import com.chrynan.common.validation.error.InvalidUriStringError
import com.chrynan.common.validation.validator.UriStringValidator
import javax.inject.Inject

class UriStringValidatorSource @Inject constructor() : UriStringValidator {

    companion object {

        /**
         * A Regex Pattern for URIs. This should work for RFC-3986. This was taken from the
         * following StackOverflow answer:
         * https://stackoverflow.com/a/17813893/1478764
         */
        private val URI_REGEX_STRING =
            """
                    [A-Za-z][A-Za-z0-9+\\-.]* :
                    (?: //
                    (?: (?:[A-Za-z0-9\\-._~!\$&'()*+,;=:]|%[0-9A-Fa-f]{2})* @)?
                    (?:
                    \\[
                    (?:
                    (?:
                    (?:                                                    (?:[0-9A-Fa-f]{1,4}:)    {6}
                    |                                                   :: (?:[0-9A-Fa-f]{1,4}:)    {5}
                    | (?:                            [0-9A-Fa-f]{1,4})? :: (?:[0-9A-Fa-f]{1,4}:)    {4}
                    | (?: (?:[0-9A-Fa-f]{1,4}:){0,1} [0-9A-Fa-f]{1,4})? :: (?:[0-9A-Fa-f]{1,4}:)    {3}
                    | (?: (?:[0-9A-Fa-f]{1,4}:){0,2} [0-9A-Fa-f]{1,4})? :: (?:[0-9A-Fa-f]{1,4}:)    {2}
                    | (?: (?:[0-9A-Fa-f]{1,4}:){0,3} [0-9A-Fa-f]{1,4})? ::    [0-9A-Fa-f]{1,4}:
                    | (?: (?:[0-9A-Fa-f]{1,4}:){0,4} [0-9A-Fa-f]{1,4})? ::
                    ) (?:
                    [0-9A-Fa-f]{1,4} : [0-9A-Fa-f]{1,4}
                    | (?: (?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) \\.){3}
                    (?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)
                    )
                    |   (?: (?:[0-9A-Fa-f]{1,4}:){0,5} [0-9A-Fa-f]{1,4})? ::    [0-9A-Fa-f]{1,4}
                    |   (?: (?:[0-9A-Fa-f]{1,4}:){0,6} [0-9A-Fa-f]{1,4})? ::
                    )
                    | [Vv][0-9A-Fa-f]+\\.[A-Za-z0-9\\-._~!\$&'()*+,;=:]+
                    )
                    \\]
                    | (?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}
                    (?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)
                    | (?:[A-Za-z0-9\\-._~!\$&'()*+,;=]|%[0-9A-Fa-f]{2})*
                    )
                    (?: : [0-9]* )?
                    (?:/ (?:[A-Za-z0-9\\-._~!\$&'()*+,;=:@]|%[0-9A-Fa-f]{2})* )*
                    | /
                    (?:    (?:[A-Za-z0-9\\-._~!\$&'()*+,;=:@]|%[0-9A-Fa-f]{2})+
                    (?:/ (?:[A-Za-z0-9\\-._~!\$&'()*+,;=:@]|%[0-9A-Fa-f]{2})* )*
                    )?
                    |        (?:[A-Za-z0-9\\-._~!\$&'()*+,;=:@]|%[0-9A-Fa-f]{2})+
                    (?:/ (?:[A-Za-z0-9\\-._~!\$&'()*+,;=:@]|%[0-9A-Fa-f]{2})* )*
                    |
                    )
                    (?:\\? (?:[A-Za-z0-9\\-._~!\$&'()*+,;=:@/?]|%[0-9A-Fa-f]{2})* )?
                    (?:\\# (?:[A-Za-z0-9\\-._~!\$&'()*+,;=:@/?]|%[0-9A-Fa-f]{2})* )?
                """.trimIndent()

        private val URI_REGEX = Regex(URI_REGEX_STRING)
    }

    override fun invoke(input: UriString?): ValidationResult {
        if (input.isNullOrBlank()) return ValidationResult.Invalid(EmptyUriStringError)

        val isWebUrl = Patterns.WEB_URL.matcher(input).matches()

        if (isWebUrl) return ValidationResult.Valid

        val isValidUri = URI_REGEX.matches(input)

        return if (isValidUri) ValidationResult.Valid else ValidationResult.Invalid(
            InvalidUriStringError
        )
    }
}