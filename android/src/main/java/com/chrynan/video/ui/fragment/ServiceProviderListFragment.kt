package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.common.model.core.UriString
import com.chrynan.video.R
import com.chrynan.video.di.qualifier.ServiceProviderListQualifier
import com.chrynan.video.navigator.ServiceProviderListNavigator
import com.chrynan.video.presenter.ServiceProviderListPresenter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.provider.ServiceProviderListItemAdapter
import com.chrynan.video.ui.view.ServiceProviderListView
import com.chrynan.video.viewmodel.ServiceProviderListItemViewModel
import kotlinx.android.synthetic.main.fragment_service_provider_list.*
import javax.inject.Inject

class ServiceProviderListFragment : BaseFragment(),
    ServiceProviderListView,
    ServiceProviderListNavigator,
    ServiceProviderListItemAdapter.ServiceProviderListItemSelectedListener {

    companion object {

        fun newInstance() = ServiceProviderListFragment()
    }

    @Inject
    override lateinit var presenter: ServiceProviderListPresenter

    @Inject
    @field:ServiceProviderListQualifier.Adapter
    lateinit var managerAdapter: RecyclerViewAdapter

    @Inject
    @field:ServiceProviderListQualifier.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_service_provider_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serviceProviderListRecyclerView?.adapter = managerAdapter
        serviceProviderListRecyclerView?.layoutManager = linearLayoutManager

        serviceProviderListFAB?.setOnClickListener { goToAddNewService() }

        presenter.loadItems()
    }

    override fun goToAddNewService() = goToFragment(NewServiceProviderFragment.newInstance())

    override fun goToServiceDetails(providerUri: UriString) =
        goToFragment(ServiceProviderDetailsFragment.newInstance(providerUri))

    override fun onServiceProviderListItemSelected(item: ServiceProviderListItemViewModel) =
        goToServiceDetails(item.uri)
}