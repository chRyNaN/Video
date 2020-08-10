package com.chrynan.video.ui.adapter.core

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.coroutine.AdapterCoroutineScope
import com.chrynan.video.presentation.viewmodel.AdapterItem
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseAdapter<VM : AdapterItem>(protected val dispatchers: CoroutineDispatchers) :
    AnotherAdapter<VM>(),
    AdapterCoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = job

    private val job = SupervisorJob()

    fun enter(view: View?, item: Any?) {
        castOrNull(item)?.let { onEnter(view, it) }
    }

    fun exit(view: View?, item: Any?) {
        castOrNull(item)?.let { onExit(view, it) }
    }

    fun recycle(view: View?, item: Any?) {
        castOrNull(item)?.let { onRecycled(view, it) }
    }

    @CallSuper
    open fun onEnter(view: View?, item: VM?) {

    }

    @CallSuper
    open fun onExit(view: View?, item: VM?) {
        job.cancel() // TODO test this works as intended
    }

    @CallSuper
    open fun onRecycled(view: View?, item: VM?) {

    }

    @CallSuper
    open fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {

    }

    @Suppress("UNCHECKED_CAST")
    private fun castOrNull(item: Any?): VM? =
        try {
            item as? VM
        } catch (e: Exception) {
            null
        }
}