package com.chrynan.video.ui.activity

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.chrynan.video.R
import com.chrynan.video.model.VideoLoadType
import com.chrynan.video.presentation.navigator.DeepLinkScreen
import com.chrynan.video.utils.getFullMimeType
import java.util.*

class DeepLinkActivity : BaseActivity<DeepLinkScreen>() {

    companion object {

        private const val SCHEME_FILE = "file"
        private const val SCHEME_CONTENT = "content"
        private const val SCHEME_HTTP = "http"
        private const val SCHEME_HTTPS = "https"
        private const val SCHEME_VIDEO = "video"
        private const val SCHEME_LBRY = "lbry"

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
        val mimeType = uri?.getFullMimeType(this)
        val isVideoMimeType = mimeType != null && mimeType.startsWith(MIME_TYPE_START_VIDEO)

        when {
            scheme == SCHEME_FILE && isVideoMimeType -> handleGenericContentUri(uri)
            scheme == SCHEME_CONTENT && isVideoMimeType -> handleGenericContentUri(uri)
            scheme == SCHEME_HTTP && isVideoMimeType -> handleGenericContentUri(uri)
            scheme == SCHEME_HTTPS && isVideoMimeType -> handleGenericContentUri(uri)
            scheme == SCHEME_VIDEO -> handleOpenVideoUri(uri)
            scheme == SCHEME_LBRY -> handleLbryUri(uri)
            else -> handleUnsupportedUri()
        }

        finish()
    }

    override fun goTo(screen: DeepLinkScreen) {

    }

    private fun handleGenericContentUri(uri: Uri) =
        startActivity(VideoPlayerActivity.newIntent(this, VideoLoadType.GenericContentUri(uri)))

    private fun handleOpenVideoUri(uri: Uri) {
        val providerUri =
            uri.getQueryParameter(QUERY_PARAM_PROVIDER_URI)?.let { Uri.parse(Uri.decode(it)) }
        val videoId = uri.getQueryParameter(QUERY_PARAM_VIDEO_ID)
        val autoPlay = uri.getQueryParameter(QUERY_PARAM_AUTO_PLAY)?.toBoolean() ?: true
        val start = uri.getQueryParameter(QUERY_PARAM_START)?.toLong()
        val end = uri.getQueryParameter(QUERY_PARAM_END)?.toLong()

        if (providerUri != null && videoId != null) {
            val loadType = VideoLoadType.OpenVideoUri(
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

    private fun handleLbryUri(uri: Uri) {
        // TODO
    }

    private fun handleUnsupportedUri() {
        Toast.makeText(this, R.string.uri_unsupported_message, Toast.LENGTH_LONG).show()

        finish()
    }
}