package com.chrynan.video.mapper.video

import com.chrynan.common.model.api.VideoAction
import com.chrynan.common.model.api.VideoInfo
import com.chrynan.common.model.api.VideoResult
import com.chrynan.video.R
import com.chrynan.common.mapper.Mapper
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