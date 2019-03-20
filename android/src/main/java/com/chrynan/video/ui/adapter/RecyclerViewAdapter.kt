package com.chrynan.video.ui.adapter

import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.presentation.viewmodel.UniqueListItem

class RecyclerViewAdapter(adapters: Set<AnotherAdapter<*>> = emptySet()) :
    ManagerRecyclerViewAdapter<UniqueListItem>(adapters = adapters) {

    override fun getItemId(position: Int) = items[position].uniqueListId
}