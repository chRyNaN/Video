package com.chrynan.video.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.chrynan.common.model.core.ID
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.ChannelScreen
import com.chrynan.video.ui.fragment.ChannelFragment

class ChannelActivity : BaseActivity<ChannelScreen>() {

    companion object {

        private const val KEY_PROVIDER_URI = "keyProviderUri"
        private const val KEY_CHANNEL_ID = "keyChannelId"

        fun newIntent(context: Context, providerUri: String, channelId: ID) =
            Intent(context, ChannelActivity::class.java).apply {
                putExtra(KEY_PROVIDER_URI, providerUri)
                putExtra(KEY_CHANNEL_ID, channelId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)

        val providerUri = intent?.getStringExtra(KEY_PROVIDER_URI)
        val channelId = intent?.getStringExtra(KEY_CHANNEL_ID)

        if (providerUri != null && channelId != null) {
            goToFragment(
                ChannelFragment.newInstance(
                    providerUri = providerUri,
                    channelId = channelId
                )
            )
        }
    }

    override fun goTo(screen: ChannelScreen) {

    }
}