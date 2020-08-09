package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.GenericContentVideoDetailsScreen
import com.chrynan.video.presentation.state.GenericContentVideoDetailsChange
import com.chrynan.video.presentation.state.GenericContentVideoDetailsIntent
import com.chrynan.video.presentation.state.GenericContentVideoDetailsState
import com.chrynan.video.presentation.presenter.GenericContentVideoDetailsPresenter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenericContentVideoDetailsFragment :
    BaseFragment<GenericContentVideoDetailsIntent, GenericContentVideoDetailsState, GenericContentVideoDetailsChange, GenericContentVideoDetailsScreen>() {

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

    override fun intents(): Flow<GenericContentVideoDetailsIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: GenericContentVideoDetailsState) {
        TODO("Not yet implemented")
    }

    override fun goTo(screen: GenericContentVideoDetailsScreen) {
        TODO("Not yet implemented")
    }
}