package com.chrynan.video.presentation.navigator

interface Navigator<S : Screen> {

    fun goBack()

    fun goUp() = goBack()

    fun goTo(screen: S)
}