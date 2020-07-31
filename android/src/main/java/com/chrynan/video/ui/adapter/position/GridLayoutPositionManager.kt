package com.chrynan.video.ui.adapter.position

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

class GridLayoutPositionManager(private val gridLayoutManager: GridLayoutManager) :
    AdapterPositionManager {

    override fun findFirstVisibleItemPosition(): Int =
        gridLayoutManager.findFirstVisibleItemPosition()

    override fun findLastVisibleItemPosition(): Int =
        gridLayoutManager.findLastVisibleItemPosition()

    override fun findViewByPosition(position: Int): View? =
        gridLayoutManager.findViewByPosition(position)

    override fun getPosition(view: View): Int = gridLayoutManager.getPosition(view)
}