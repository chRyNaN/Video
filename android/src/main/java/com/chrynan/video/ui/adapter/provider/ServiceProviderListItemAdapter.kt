package com.chrynan.video.ui.adapter.provider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.viewmodel.ServiceProviderListItemViewModel
import javax.inject.Inject

@Adapter
class ServiceProviderListItemAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<ServiceProviderListItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ServiceProviderListItemAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is ServiceProviderListItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_service_provider_list_item, parent, false)

    override fun View.onBindItem(item: ServiceProviderListItemViewModel, position: Int) {

    }
}