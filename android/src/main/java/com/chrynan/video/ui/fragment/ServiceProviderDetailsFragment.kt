package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.ui.view.ServiceProviderDetailsView
import com.chrynan.video.R
import com.chrynan.video.navigator.ServiceProviderDetailsNavigator
import com.chrynan.video.presenter.ServiceProviderDetailsPresenter
import javax.inject.Inject

class ServiceProviderDetailsFragment : BaseFragment(),
    ServiceProviderDetailsView,
    ServiceProviderDetailsNavigator {

    companion object {

        fun newInstance() = ServiceProviderDetailsFragment()
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
}