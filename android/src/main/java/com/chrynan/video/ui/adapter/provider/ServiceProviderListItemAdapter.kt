package com.chrynan.video.ui.adapter.provider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.presentation.viewmodel.ServiceProviderListItemViewModel
import kotlinx.android.synthetic.main.adapter_service_provider_list_item.view.*
import javax.inject.Inject

@Adapter
class ServiceProviderListItemAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: ServiceProviderListItemSelectedListener
) : BaseAdapter<ServiceProviderListItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(ServiceProviderListItemAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is ServiceProviderListItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_service_provider_list_item, parent, false)

    override fun View.onBindItem(item: ServiceProviderListItemViewModel, position: Int) {
        adapterServiceProviderNameTextView?.text = item.name
        adapterServiceProviderUriTextView?.text = item.uri
        adapterServiceProviderThumbnailImageWidget?.load(item.imageUri) {
            placeholder(R.drawable.ic_default_user)
            error(R.drawable.ic_default_user)
        }
        setOnClickListener { listener.onServiceProviderListItemSelected(item = item) }
    }

    interface ServiceProviderListItemSelectedListener {

        fun onServiceProviderListItemSelected(item: ServiceProviderListItemViewModel)
    }
}