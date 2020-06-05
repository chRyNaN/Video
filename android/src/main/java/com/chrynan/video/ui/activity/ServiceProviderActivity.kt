package com.chrynan.video.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.chrynan.common.model.core.UriString
import com.chrynan.video.R
import com.chrynan.video.model.ServiceProviderScreen
import com.chrynan.video.navigator.ServiceProviderNavigator
import com.chrynan.video.parcel.model.getScreen
import com.chrynan.video.parcel.model.putScreen
import com.chrynan.video.ui.fragment.ServiceProviderListFragment

class ServiceProviderActivity : BaseActivity(),
    ServiceProviderNavigator {

    companion object {

        private const val KEY_SCREEN = "screen"

        fun newIntent(
            context: Context,
            screen: ServiceProviderScreen = ServiceProviderScreen.List
        ) = Intent(context, ServiceProviderActivity::class.java).apply {
            putScreen(KEY_SCREEN, screen)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_provider)

        val screen = intent?.getScreen(KEY_SCREEN) ?: ServiceProviderScreen.List

        when (screen) {
            is ServiceProviderScreen.List -> goToServiceList()
            is ServiceProviderScreen.New -> goToAddNewService()
            is ServiceProviderScreen.Details -> goToServiceDetails(screen.providerUri)
        }
    }

    override fun goToServiceList() = goToFragment(ServiceProviderListFragment.newInstance())

    override fun goToAddNewService() {
    }

    override fun goToServiceDetails(providerUri: UriString) {

    }
}