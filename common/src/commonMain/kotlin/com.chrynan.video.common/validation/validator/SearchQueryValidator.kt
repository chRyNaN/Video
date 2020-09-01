package com.chrynan.video.common.validation.validator

import com.chrynan.inject.Inject
import com.chrynan.video.common.model.SearchQuery
import com.chrynan.video.common.validation.core.ValidationResult
import com.chrynan.video.common.validation.core.Validator
import com.chrynan.video.common.validation.error.EmptySearchQueryError

class SearchQueryValidator @Inject constructor() :
    Validator<SearchQuery, String> {

    override fun invoke(input: SearchQuery): ValidationResult<String> {
        if (input.query.isNullOrBlank()) return ValidationResult.Invalid(
            EmptySearchQueryError
        )

        val formattedQuery = input.query + input.selectedTags.joinToString(prefix = " #")

        return ValidationResult.Valid(formattedQuery)
    }
}