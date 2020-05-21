package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.viewmodel.SectionHeaderViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import kotlinx.android.synthetic.main.adapter_section_header.view.*
import javax.inject.Inject

@Adapter
class SectionHeaderAdapter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BaseAdapter<SectionHeaderViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is SectionHeaderViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_section_header, parent, false)

    override fun View.onBindItem(item: SectionHeaderViewModel, position: Int) {
        adapterSectionHeaderTextView?.text = item.header
    }
}