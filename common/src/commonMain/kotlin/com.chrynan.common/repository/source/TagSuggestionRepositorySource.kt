package com.chrynan.common.repository.source

import com.chrynan.common.Inject
import com.chrynan.common.model.TagSuggestion
import com.chrynan.common.repository.TagSuggestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TagSuggestionRepositorySource @Inject constructor() : TagSuggestionRepository {

    override fun getSuggestions(): Flow<List<TagSuggestion>> = flowOf(
        listOf( // TODO update with real data
            TagSuggestion(name = "One"),
            TagSuggestion(name = "Two"),
            TagSuggestion(name = "Three")
        )
    )
}