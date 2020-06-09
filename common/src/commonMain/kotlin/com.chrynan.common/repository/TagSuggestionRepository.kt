package com.chrynan.common.repository

import com.chrynan.common.model.TagSuggestion
import kotlinx.coroutines.flow.Flow

interface TagSuggestionRepository {

    fun getSuggestions(): Flow<List<TagSuggestion>>
}