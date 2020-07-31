package com.chrynan.video.ui.adapter.position

import android.view.View

interface AdapterPositionManager {

    fun findFirstVisibleItemPosition(): Int

    fun findLastVisibleItemPosition(): Int

    fun findViewByPosition(position: Int): View?

    fun getPosition(view: View): Int
}