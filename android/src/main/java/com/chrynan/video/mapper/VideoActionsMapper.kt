package com.chrynan.video.mapper

import com.chrynan.common.model.VideoAction
import com.chrynan.common.model.VideoInfo
import com.chrynan.common.model.VideoResult
import com.chrynan.video.R
import com.chrynan.video.viewmodel.VideoInfoActionViewModel
import javax.inject.Inject

class VideoActionsMapper @Inject constructor() :
    Mapper<VideoResult, List<VideoInfoActionViewModel>> {

    override suspend fun map(model: VideoResult): List<VideoInfoActionViewModel> =
        model.video.actions.map {
            val iconResId = when (it) {
                is VideoAction.Share -> R.drawable.ic_video_action_share
                is VideoAction.Cast -> R.drawable.ic_video_action_cast
                is VideoAction.Flag -> R.drawable.ic_video_action_flag
                is VideoAction.Download -> R.drawable.ic_video_action_download
                is VideoAction.RatingUp -> R.drawable.ic_video_action_rating_up
                is VideoAction.RatingDown -> R.drawable.ic_video_action_rating_down
            }

            VideoInfoActionViewModel(
                videoInfo = model.info,
                action = it,
                icon = iconResId
            )
        } + getLocalActions(model.info)

    private fun getLocalActions(info: VideoInfo): List<VideoInfoActionViewModel> {
        val shareAction = VideoInfoActionViewModel(
            videoInfo = info,
            action = VideoAction.Share(info.videoUri), // TODO make sure this is the correct URI to share
            icon = R.drawable.ic_video_action_share
        )

        val castAction = VideoInfoActionViewModel(
            videoInfo = info,
            action = VideoAction.Cast,
            icon = R.drawable.ic_video_action_cast
        )

        return listOf(shareAction, castAction)
    }
}