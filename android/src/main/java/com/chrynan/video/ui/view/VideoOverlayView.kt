package com.chrynan.video.ui.view

import androidx.recyclerview.widget.RecyclerView
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter

interface VideoOverlayView : View,
    OverlayView {

    fun setupAdapter(
        adapter: RecyclerViewAdapter,
        layoutManager: RecyclerView.LayoutManager,
        decorator: RecyclerView.ItemDecoration
    )
}