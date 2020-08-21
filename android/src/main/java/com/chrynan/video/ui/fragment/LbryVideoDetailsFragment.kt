package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.LbryVideoDetailsScreen
import com.chrynan.video.presentation.state.LbryVideoDetailsChange
import com.chrynan.video.presentation.state.LbryVideoDetailsIntent
import com.chrynan.video.presentation.state.LbryVideoDetailsState
import com.chrynan.video.presentation.presenter.LbryVideoDetailsPresenter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LbryVideoDetailsFragment :
    BaseFragment<LbryVideoDetailsIntent, LbryVideoDetailsState, LbryVideoDetailsChange, LbryVideoDetailsScreen>() {

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

    override fun intents(): Flow<LbryVideoDetailsIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: LbryVideoDetailsState) {
        super.render(state)

        TODO("Not yet implemented")
    }

    override fun goTo(screen: LbryVideoDetailsScreen) {
        TODO("Not yet implemented")
    }
}