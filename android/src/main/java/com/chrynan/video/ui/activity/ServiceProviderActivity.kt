package com.chrynan.video.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.chrynan.video.R
import com.chrynan.video.parcel.model.getScreen
import com.chrynan.video.presentation.navigator.ServiceProviderScreen
import com.chrynan.video.ui.fragment.NewServiceProviderFragment
import com.chrynan.video.ui.fragment.ServiceProviderDetailsFragment
import com.chrynan.video.ui.fragment.ServiceProviderListFragment

class ServiceProviderActivity : BaseActivity<ServiceProviderScreen>() {

    companion object {

        private const val KEY_SCREEN = "screen"

        fun newIntent(
            context: Context,
            screen: ServiceProviderScreen = ServiceProviderScreen.List
        ) = Intent(context, ServiceProviderActivity::class.java).apply {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_provider)

        val screen = intent?.getScreen(KEY_SCREEN) ?: ServiceProviderScreen.List


    }

    override fun goTo(screen: ServiceProviderScreen) {
        when (screen) {
            is ServiceProviderScreen.Details -> goToFragment(
                ServiceProviderDetailsFragment.newInstance(
                    screen.providerUri
                )
            )
            is ServiceProviderScreen.List -> goToFragment(ServiceProviderListFragment.newInstance())
            is ServiceProviderScreen.New -> goToFragment(NewServiceProviderFragment.newInstance())
        }
    }
}