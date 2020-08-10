package com.chrynan.video.common.repository

import com.chrynan.video.common.model.TagSuggestion
import kotlinx.coroutines.flow.Flow

interface TagSuggestionRepository {

    fun getSuggestions(): Flow<List<TagSuggestion>>
}