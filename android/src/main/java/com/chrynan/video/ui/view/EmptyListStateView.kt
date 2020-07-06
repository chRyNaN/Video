package com.chrynan.video.ui.view

interface EmptyListStateView : View {

    fun showEmptyState()

    fun showListState()
}


fun EmptyListStateView.toggleEmptyState(count: Int) =
    if (count > 0) {
        showListState()
    } else {
        showEmptyState()
    }