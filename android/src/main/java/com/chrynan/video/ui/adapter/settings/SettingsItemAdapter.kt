package com.chrynan.video.ui.adapter.settings

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
import com.chrynan.video.presentation.viewmodel.SettingsItemViewModel
import kotlinx.android.synthetic.main.adapter_settings_item.view.*
import javax.inject.Inject

@Adapter
class SettingsItemAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: SettingsItemSelectedListener
) : BaseAdapter<SettingsItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(SettingsItemAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is SettingsItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_settings_item, parent, false)

    override fun View.onBindItem(item: SettingsItemViewModel, position: Int) {
        adapterSettingsItemTitleTextView?.text = item.title

        if (item.iconResourceID == null) {
            adapterSettingsItemIconImageView?.visibility = View.GONE
        } else {
            adapterSettingsItemIconImageView?.visibility = View.VISIBLE
            adapterSettingsItemIconImageView?.load(item.iconResourceID)
        }

        adapterSettingsItemEndTextView?.visibility =
            if (item.endText.isNullOrBlank()) View.GONE else View.VISIBLE
        adapterSettingsItemEndTextView?.text = item.endText

        adapterSettingsItemDescriptionTextView?.visibility =
            if (item.description.isNullOrBlank()) View.GONE else View.VISIBLE
        adapterSettingsItemDescriptionTextView?.text = item.description

        if (item.isSelectable) {
            setOnClickListener { listener.onSettingsItemSelected(type = item.type) }
        }
    }

    interface SettingsItemSelectedListener {

        fun onSettingsItemSelected(type: SettingsItemViewModel.SettingsType)
    }
}