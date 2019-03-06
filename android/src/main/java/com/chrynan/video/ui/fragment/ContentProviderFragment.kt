package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.ui.view.ContentProviderView

class ContentProviderFragment : BaseFragment(),
    ContentProviderView {

    companion object {

        fun newInstance() = ContentProviderFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_content_provider, container, false)
}