package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.presenter.GenericContentVideoDetailsPresenter
import com.chrynan.video.ui.view.GenericContentVideoDetailsView
import javax.inject.Inject

class GenericContentVideoDetailsFragment : BaseFragment(),
    GenericContentVideoDetailsView {

    companion object {

        fun newIntent() = GenericContentVideoDetailsFragment()
    }

    @Inject
    override lateinit var presenter: GenericContentVideoDetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_generic_content_video_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}