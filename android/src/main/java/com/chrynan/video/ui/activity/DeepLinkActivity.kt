package com.chrynan.video.ui.activity

import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import java.util.*

class DeepLinkActivity : BaseActivity() {

    companion object {

        private const val SCHEME_FILE = "file"
        private const val SCHEME_CONTENT = "content"
        private const val SCHEME_HTTP = "http"
        private const val SCHEME_HTTPS = "https"

        private const val MIME_TYPE_START_VIDEO = "video"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent?.data
        val scheme = uri?.scheme?.toLowerCase(Locale.getDefault())
        val mimeType = uri?.fullMimeType
        val isVideoMimeType = mimeType != null && mimeType.startsWith(MIME_TYPE_START_VIDEO)

        when {
            scheme == SCHEME_FILE && isVideoMimeType -> handleVideoContentUri(uri)
            scheme == SCHEME_CONTENT && isVideoMimeType -> handleVideoContentUri(uri)
            scheme == SCHEME_HTTP && isVideoMimeType -> handleVideoContentUri(uri)
            scheme == SCHEME_HTTPS && isVideoMimeType -> handleVideoContentUri(uri)
            else -> handleUnsupportedUri()
        }

        finish()
    }

    private fun handleVideoContentUri(uri: Uri) =
        startActivity(VideoPlayerActivity.newIntent(this, uri))

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