package com.chrynan.video.ui.adapter.position

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

class LinearLayoutPositionManager(private val linearLayoutManager: LinearLayoutManager) :
    AdapterPositionManager {

    override fun findFirstVisibleItemPosition(): Int =
        linearLayoutManager.findFirstVisibleItemPosition()

    override fun findLastVisibleItemPosition(): Int =
        linearLayoutManager.findLastVisibleItemPosition()

    override fun findViewByPosition(position: Int): View? =
        linearLayoutManager.findViewByPosition(position)

    override fun getPosition(view: View): Int = linearLayoutManager.getPosition(view)
}