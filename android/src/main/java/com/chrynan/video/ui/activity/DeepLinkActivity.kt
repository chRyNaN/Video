package com.chrynan.video.ui.activity

import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import com.chrynan.video.model.VideoLoadType
import java.util.*

class DeepLinkActivity : BaseActivity() {

    companion object {

        private const val SCHEME_FILE = "file"
        private const val SCHEME_CONTENT = "content"
        private const val SCHEME_HTTP = "http"
        private const val SCHEME_HTTPS = "https"

        private const val MIME_TYPE_START_VIDEO = "video"

        private const val QUERY_PARAM_PROVIDER_URI = "provider_uri"
        private const val QUERY_PARAM_VIDEO_ID = "video_id"
        private const val QUERY_PARAM_AUTO_PLAY = "auto_play"
        private const val QUERY_PARAM_START = "start"
        private const val QUERY_PARAM_END = "end"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent?.data
        val scheme = uri?.scheme?.toLowerCase(Locale.getDefault())
        val mimeType = uri?.fullMimeType
        val isVideoMimeType = mimeType != null && mimeType.startsWith(MIME_TYPE_START_VIDEO)

        when {
            scheme == SCHEME_FILE && isVideoMimeType -> handleContentUri(uri)
            scheme == SCHEME_CONTENT && isVideoMimeType -> handleContentUri(uri)
            scheme == SCHEME_HTTP && isVideoMimeType -> handleContentUri(uri)
            scheme == SCHEME_HTTPS && isVideoMimeType -> handleContentUri(uri)
            else -> handleUnsupportedUri()
        }

        finish()
    }

    private fun handleContentUri(uri: Uri) =
        startActivity(VideoPlayerActivity.newIntent(this, VideoLoadType.ContentUri(uri)))

    private fun handleVideoUri(uri: Uri) {
        val providerUri =
            uri.getQueryParameter(QUERY_PARAM_PROVIDER_URI)?.let { Uri.parse(Uri.decode(it)) }
        val videoId = uri.getQueryParameter(QUERY_PARAM_VIDEO_ID)
        val autoPlay = uri.getQueryParameter(QUERY_PARAM_AUTO_PLAY)?.toBoolean() ?: true
        val start = uri.getQueryParameter(QUERY_PARAM_START)?.toLong()
        val end = uri.getQueryParameter(QUERY_PARAM_END)?.toLong()

        if (providerUri != null && videoId != null) {
            val loadType = VideoLoadType.VideoUri(
                providerUri = providerUri,
                videoId = videoId,
                autoPlay = autoPlay,
                start = start,
                end = end
            )

            startActivity(VideoPlayerActivity.newIntent(this, loadType))
        } else {
            handleUnsupportedUri()
        }
    }

    private fun handleUnsupportedUri() {

    }

    private val Uri.fullMimeType: String?
        get() {
            var type = contentResolver.getType(this)

            if (type == null) {
                val retriever = MediaMetadataRetriever().apply {
                    setDataSource(this@DeepLinkActivity, this@fullMimeType)
                }

                type = try {
                    retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE)
                } catch (throwable: Throwable) {
                    null
                }
            }

            if (type.isNullOrBlank()) {
                type = null
            }

            return type
        }
}