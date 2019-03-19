package com.chrynan.video.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.presentation.view.QueueView
import com.chrynan.video.R
import com.chrynan.video.model.QueueItemViewModel
import com.chrynan.video.model.VideoInfo
import com.chrynan.video.ui.adapter.listener.QueueOptionsListener
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_queue.*
import javax.inject.Inject

class QueueFragment : BaseFragment(),
    QueueView,
    QueueOptionsListener {

    @Inject
    lateinit var managerAdapter: ManagerRecyclerViewAdapter<UniqueAdapterItem>

    private val videoOptionsMenuBottomSheet by lazy { MenuBottomSheetDialogFragment.newInstance(menuResId = R.menu.menu_video_queue_options) }

    companion object {

        fun newInstance() = QueueFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_queue, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        queueItemRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            val videoInfo = VideoInfo(
                videoId = "",
                channelId = "",
                providerUri = Uri.parse(""),
                videoUri = Uri.parse("")
            )

            managerAdapter.items = listOf(
                QueueItemViewModel(
                    videoInfo = videoInfo,
                    videoImageUri = Uri.parse(""),
                    title = "Test Title",
                    isActivated = false,
                    position = 0
                )
            )
        }
    }

    override fun queueOptionsMenuSelected(videoInfo: VideoInfo) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }
}