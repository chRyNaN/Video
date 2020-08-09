package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.chrynan.logger.Logger
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.binder.SearchTagAdapterComponentsBinder
import com.chrynan.video.ui.adapter.core.AdapterComponents
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.utils.onEnterPressed
import com.chrynan.video.presentation.viewmodel.TagItemViewModel
import kotlinx.android.synthetic.main.widget_search.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.flowOf
import kotlin.coroutines.CoroutineContext

class SearchWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr),
    CoroutineScope {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_search, this)

        orientation = VERTICAL

        searchWidgetEditText?.setBackgroundShape(BackgroundShape.Round)
    }

    override val coroutineContext: CoroutineContext
        get() = job

    var text: String? = null
        get() = searchWidgetEditText?.text?.toString()
        set(value) {
            field = value

            searchWidgetEditText?.setText(value)
        }

    private lateinit var job: Job

    private var tagAdapterComponents: AdapterComponents? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        job = SupervisorJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        job.cancel()
    }

    fun setupTagAdapter(binder: SearchTagAdapterComponentsBinder) {
        tagAdapterComponents = searchWidgetTagRecyclerView?.let { binder.bindRecyclerView(it) }
    }

    fun updateTags(tags: Set<TagItemViewModel>) {
        tagAdapterComponents?.let { components ->
            flowOf(tags)
                .calculateAndDispatchDiff(components.adapterItemHandler)
                .catch {
                    Logger.logError(
                        throwable = it,
                        message = "Error updating tags in SearchWidget. Tags = $tags."
                    )
                }
                .launchIn(this@SearchWidget)
        }
    }

    fun onEnterPressed(action: () -> Unit) {
        searchWidgetEditText?.onEnterPressed(action)
    }
}