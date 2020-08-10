package com.chrynan.video.common.repository.source

import com.chrynan.video.common.Inject
import com.chrynan.video.common.model.TagSuggestion
import com.chrynan.video.common.repository.TagSuggestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TagSuggestionRepositorySource @Inject constructor() :
    TagSuggestionRepository {

    override fun getSuggestions(): Flow<List<TagSuggestion>> = flowOf(
        listOf( // TODO update with real data
            TagSuggestion(name = "One"),
            TagSuggestion(name = "Two"),
            TagSuggestion(name = "Three")
        )
    )
}