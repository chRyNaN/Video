package com.chrynan.video.ui.view

interface NewServiceProviderView : View {

    fun showProviderUriValid()

    fun showProviderUriInvalid(errorText: String)
}