package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.ServiceProviderListScreen
import com.chrynan.video.presentation.state.ServiceProviderListChange
import com.chrynan.video.presentation.state.ServiceProviderListIntent
import com.chrynan.video.presentation.state.ServiceProviderListState
import com.chrynan.video.presentation.presenter.ServiceProviderListPresenter
import com.chrynan.video.ui.adapter.factory.ServiceProviderListAdapterFactory
import com.chrynan.video.ui.adapter.factory.bindAdapterFactory
import com.chrynan.video.ui.adapter.provider.ServiceProviderListItemAdapter
import com.chrynan.video.viewmodel.ServiceProviderListItemViewModel
import kotlinx.android.synthetic.main.fragment_service_provider_list.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceProviderListFragment :
    BaseFragment<ServiceProviderListIntent, ServiceProviderListState, ServiceProviderListChange, ServiceProviderListScreen>(),
    ServiceProviderListItemAdapter.ServiceProviderListItemSelectedListener {

    companion object {

        fun newInstance() = ServiceProviderListFragment()
    }

    @Inject
    override lateinit var presenter: ServiceProviderListPresenter

    @Inject
    lateinit var adapterFactory: ServiceProviderListAdapterFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_service_provider_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serviceProviderListRecyclerView?.bindAdapterFactory(adapterFactory)

        serviceProviderListFAB?.setOnClickListener { }
    }

    override fun intents(): Flow<ServiceProviderListIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: ServiceProviderListState) {
        TODO("Not yet implemented")
    }

    override fun goTo(screen: ServiceProviderListScreen) {
        TODO("Not yet implemented")
    }

    override fun onServiceProviderListItemSelected(item: ServiceProviderListItemViewModel) {

    }
}