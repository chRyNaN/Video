package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.common.model.core.UriString
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.ServiceProviderDetailsScreen
import com.chrynan.video.presentation.state.ServiceProviderDetailsChange
import com.chrynan.video.presentation.state.ServiceProviderDetailsIntent
import com.chrynan.video.presentation.state.ServiceProviderDetailsState
import com.chrynan.video.presentation.presenter.ServiceProviderDetailsPresenter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceProviderDetailsFragment :
    BaseFragment<ServiceProviderDetailsIntent, ServiceProviderDetailsState, ServiceProviderDetailsChange, ServiceProviderDetailsScreen>() {

    companion object {

        fun newInstance(providerUri: UriString) = ServiceProviderDetailsFragment()
    }

    @Inject
    override lateinit var presenter: ServiceProviderDetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_service_provider_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun intents(): Flow<ServiceProviderDetailsIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: ServiceProviderDetailsState) {
        TODO("Not yet implemented")
    }

    override fun goTo(screen: ServiceProviderDetailsScreen) {
        TODO("Not yet implemented")
    }
}