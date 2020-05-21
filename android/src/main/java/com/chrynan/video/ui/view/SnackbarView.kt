package com.chrynan.video.ui.view

interface SnackbarView : View {

    fun showSnackbar(
        message: String,
        type: Type = Type.MESSAGE,
        length: Length = Length.SHORT,
        action: Action? = null
    )

    fun hideSnackbar()

    enum class Type {

        MESSAGE,
        ERROR
    }

    enum class Length {

        SHORT,
        LONG,
        INDEFINITE
    }

    data class Action(
        val title: String,
        val block: () -> Unit
    )
}