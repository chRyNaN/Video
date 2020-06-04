package com.chrynan.common.model.api

import com.chrynan.common.model.core.UriString

sealed class VideoAction {

    abstract val type: String

    object Cast : VideoAction(),
        LocalAction {

        override val type = "cast"
    }

    data class Share(val shareUri: UriString? = null) : VideoAction(),
        LocalAction {

        override val type = "share"
    }

    data class Download(val downloadUri: UriString) : VideoAction(),
        RemoteAction {

        override val type = "download"
    }

    data class RatingUp(override val isSelected: Boolean) : VideoAction(),
        SelectableAction,
        RemoteAction {

        override val type = "rating_up"
    }

    data class RatingDown(override val isSelected: Boolean) : VideoAction(),
        SelectableAction,
        RemoteAction {

        override val type = "rating_down"
    }

    data class Flag(override val isSelected: Boolean) : VideoAction(),
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