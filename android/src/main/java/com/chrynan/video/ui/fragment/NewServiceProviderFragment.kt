package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.NewServiceProviderScreen
import com.chrynan.video.presentation.state.NewServiceProviderChange
import com.chrynan.video.presentation.state.NewServiceProviderIntent
import com.chrynan.video.presentation.state.NewServiceProviderState
import com.chrynan.video.presentation.presenter.NewServiceProviderPresenter
import com.chrynan.video.utils.onEnterPressed
import com.chrynan.video.utils.textChanges
import kotlinx.android.synthetic.main.fragment_new_service_provider.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NewServiceProviderFragment :
    BaseFragment<NewServiceProviderIntent, NewServiceProviderState, NewServiceProviderChange, NewServiceProviderScreen>() {

    companion object {

        fun newInstance() = NewServiceProviderFragment()
    }

    @Inject
    override lateinit var presenter: NewServiceProviderPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_new_service_provider, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newServiceProviderMainActionButton?.setOnClickListener {


            newServiceProviderTextInputEditText?.onEnterPressed {

            }

            newServiceProviderTextInputEditText.textChanges()
        }
    }

    override fun intents(): Flow<NewServiceProviderIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: NewServiceProviderState) {
        super.render(state)

        TODO("Not yet implemented")
    }

    override fun goTo(screen: NewServiceProviderScreen) {
        TODO("Not yet implemented")
    }
}