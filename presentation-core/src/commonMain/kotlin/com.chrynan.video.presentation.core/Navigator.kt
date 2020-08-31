package com.chrynan.video.presentation.core

interface Navigator<S : Screen> {

    fun goBack()

    fun goUp() = goBack()

    fun goTo(screen: S)
}