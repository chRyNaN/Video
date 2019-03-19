package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.Inject
import com.chrynan.video.R
import com.chrynan.video.model.SectionHeaderViewModel
import kotlinx.android.synthetic.main.adapter_section_header.view.*

class SectionHeaderAdapter @Inject constructor() : AnotherAdapter<SectionHeaderViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is SectionHeaderViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_section_header, parent, false)

    override fun onBindItem(view: View, item: SectionHeaderViewModel) {
        view.apply {
            sectionHeaderTextView?.text = item.header
        }
    }
}