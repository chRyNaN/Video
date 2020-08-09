package com.chrynan.video.ui.adapter.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.VideoInfoAboutViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.adapter_video_info_about.view.*
import javax.inject.Inject

@Adapter
class VideoInfoAboutAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val markdownParser: Markwon
) : BaseAdapter<VideoInfoAboutViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(VideoInfoAboutAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoAboutViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View = inflater.inflate(R.layout.adapter_video_info_about, parent, false)

    override fun View.onBindItem(item: VideoInfoAboutViewModel, position: Int) {
        if (item.about != null) {
            adapterVideoInfoAboutTextView?.let { view ->
                view.visibility = View.VISIBLE

                markdownParser.setMarkdown(
                    view,
                    item.about
                )
            }
        } else {
            adapterVideoInfoAboutTextView?.visibility = View.GONE
        }
    }
}