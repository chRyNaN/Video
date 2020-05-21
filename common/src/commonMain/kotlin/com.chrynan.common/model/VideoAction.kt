package com.chrynan.common.model

import com.chrynan.common.model.core.UriString

sealed class VideoAction {

    abstract val type: String

    data class BinaryAction(
        val name: String,
        val icon: UriString,
        val isSelected: Boolean
    ) : VideoAction() {

        override val type = "binary"
    }

    data class LocalAction(
        val icon: Int,
        val localType: LocalType
    ) : VideoAction() {

        override val type = "local"

        enum class LocalType {

            SHARE
        }
    }
}