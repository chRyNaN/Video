package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class VideoAction {

    @SerialName(value = "type")
    abstract val type: String

    @Serializable
    object Cast : VideoAction(),
        LocalAction {

        override val type = "cast"
    }

    @Serializable
    data class Share(@SerialName(value = "shareUri") val shareUri: UriString? = null) :
        VideoAction(),
        LocalAction {

        override val type = "share"
    }

    @Serializable
    data class Download(@SerialName(value = "downloadUri") val downloadUri: UriString) :
        VideoAction(),
        RemoteAction {

        override val type = "download"
    }

    @Serializable
    data class RatingUp(@SerialName(value = "isSelected") override val isSelected: Boolean) :
        VideoAction(),
        SelectableAction,
        RemoteAction {

        override val type = "rating_up"
    }

    @Serializable
    data class RatingDown(@SerialName(value = "isSelected") override val isSelected: Boolean) :
        VideoAction(),
        SelectableAction,
        RemoteAction {

        override val type = "rating_down"
    }

    @Serializable
    data class Flag(@SerialName(value = "isSelected") override val isSelected: Boolean) :
        VideoAction(),
        SelectableAction,
        RemoteAction {

        override val type = "flag"
    }
}

interface SelectableAction {

    val isSelected: Boolean
}

interface LocalAction

interface RemoteAction