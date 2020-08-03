package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.presenter.LbryVideoDetailsPresenter
import com.chrynan.video.ui.view.LbryVideoDetailsView
import javax.inject.Inject

class LbryVideoDetailsFragment : BaseFragment(),
    LbryVideoDetailsView {

    companion object {

        fun newInstance() = LbryVideoDetailsFragment()
    }

    @Inject
    override lateinit var presenter: LbryVideoDetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_lbry_video_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}