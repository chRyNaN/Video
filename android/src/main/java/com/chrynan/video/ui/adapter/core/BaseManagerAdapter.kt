package com.chrynan.video.ui.adapter.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import com.chrynan.video.viewmodel.AdapterItem

open class BaseManagerAdapter<VM : AdapterItem>(
    private val adapters: Set<BaseAdapter<out VM>>,
    private val positionManager: AdapterPositionManager? = null
) : ManagerRecyclerViewAdapter<VM>(adapters = adapters) {

    companion object {

        private const val DEFAULT_LOAD_MORE_THRESHOLD = 10
    }

    var endlessScrollLoadMoreThreshold = DEFAULT_LOAD_MORE_THRESHOLD

    var endlessScrollDirection: EndlessScrollDirection = EndlessScrollDirection.TOP

    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            handleOnScrolled()
        }
    }

    private val recyclerListener = RecyclerView.RecyclerListener { handleRecycled(it.itemView) }

    private val endlessScrollListeners = mutableListOf<EndlessScrollListener>()

    private val isEndlessScrollEnabled: Boolean
        get() = endlessScrollListeners.isNotEmpty()

    private var isLoading = false
    private var itemCountBeforeLoading = 0

    private var previousFirstVisibleItemPosition = -1
    private var previousLastVisibleItemPosition = -1

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(onScrollListener)
        recyclerView.setRecyclerListener(recyclerListener)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        recyclerView.removeOnScrollListener(onScrollListener)
        recyclerView.setRecyclerListener(null)
        adapters.forEach { it.onDetachedFromRecyclerView(recyclerView) }
    }

    fun addEndlessScrollListener(listener: EndlessScrollListener) {
        endlessScrollListeners.add(listener)
    }

    fun removeEndlessScrollListener(listener: EndlessScrollListener) {
        endlessScrollListeners.remove(listener)
    }

    fun notifyDataLoaded() {
        isLoading = false
        itemCountBeforeLoading = itemCount
    }

    private fun handleOnScrolled() {
        if (positionManager != null) {
            val firstPosition = positionManager.findFirstVisibleItemPosition()
            val lastPosition = positionManager.findLastVisibleItemPosition()

            if (firstPosition != previousFirstVisibleItemPosition || lastPosition != previousLastVisibleItemPosition) {
                handleEndlessScrollLoadMore(
                    firstVisiblePosition = firstPosition,
                    lastVisiblePosition = lastPosition
                )

                val previousFirstItem = items.getOrNull(previousFirstVisibleItemPosition)
                val previousLastItem = items.getOrNull(previousLastVisibleItemPosition)
                val firstItem = items.getOrNull(firstPosition)
                val lastItem = items.getOrNull(lastPosition)

                val previousFirstView =
                    positionManager.findViewByPosition(previousFirstVisibleItemPosition)
                val previousLastView =
                    positionManager.findViewByPosition(previousLastVisibleItemPosition)
                val firstView = positionManager.findViewByPosition(firstPosition)
                val lastView = positionManager.findViewByPosition(lastPosition)

                // Top
                if (firstPosition > previousFirstVisibleItemPosition) {
                    // The old item is no longer visible
                    getAdapterForItem(previousFirstItem)?.exit(
                        view = previousFirstView,
                        item = previousFirstItem
                    )
                } else if (firstPosition < previousFirstVisibleItemPosition) {
                    // The new item is now visible
                    getAdapterForItem(firstItem)?.enter(view = firstView, item = firstItem)
                }

                // Bottom
                if (lastPosition > previousLastVisibleItemPosition) {
                    // The new item is now visible
                    getAdapterForItem(lastItem)?.enter(view = lastView, item = lastItem)
                } else if (lastPosition < previousLastVisibleItemPosition) {
                    // The old item is no longer visible
                    getAdapterForItem(previousLastItem)?.exit(
                        view = previousLastView,
                        item = previousLastItem
                    )
                }
            }
        }
    }

    private fun handleRecycled(itemView: View) {
        val position = positionManager?.getPosition(itemView)
        val item = position?.let { items.getOrNull(it) }

        getAdapterForItem(item)?.recycle(itemView, item)
    }

    private fun handleEndlessScrollLoadMore(firstVisiblePosition: Int, lastVisiblePosition: Int) {
        if (itemCountBeforeLoading != itemCount) {
            itemCountBeforeLoading = itemCount
            isLoading = false
        }

        if (isEndlessScrollEnabled and !isLoading) {
            val firstWithinThreshold = firstVisiblePosition - endlessScrollLoadMoreThreshold <= 0
            val lastWithinThreshold =
                lastVisiblePosition + endlessScrollLoadMoreThreshold >= items.lastIndex

            if ((endlessScrollDirection == EndlessScrollDirection.TOP && firstWithinThreshold) or
                (endlessScrollDirection == EndlessScrollDirection.BOTTOM && lastWithinThreshold)
            ) {
                isLoading = true
                itemCountBeforeLoading = itemCount
                endlessScrollListeners.onLoadMore()
            }
        }
    }

    private fun getAdapterForItem(item: VM?) =
        if (item != null) adapters.firstOrNull { it.onHandlesItem(item) } else null

    private fun List<EndlessScrollListener>.onLoadMore() = forEach { it.onLoadMore() }

    enum class EndlessScrollDirection {

        TOP,
        BOTTOM
    }

    interface EndlessScrollListener {

        fun onLoadMore()
    }
}