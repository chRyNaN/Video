package com.chrynan.video.common.validation.validator

import com.chrynan.inject.Inject
import com.chrynan.validator.Invalid
import com.chrynan.validator.Valid
import com.chrynan.validator.ValidationResult
import com.chrynan.validator.Validator
import com.chrynan.video.common.model.SearchQuery
import com.chrynan.video.common.validation.error.EmptySearchQueryError

class SearchQueryValidator @Inject constructor() : Validator<SearchQuery, String> {

    override fun validate(input: SearchQuery): ValidationResult<String> {
        if (input.query.isNullOrBlank()) return Invalid(EmptySearchQueryError)

        val formattedQuery = input.query + input.selectedTags.joinToString(prefix = " #")

        return Valid(formattedQuery)
    }
}