package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.presentation.navigator.SettingsScreen
import com.chrynan.video.presentation.state.SettingsChange
import com.chrynan.video.presentation.state.SettingsIntent
import com.chrynan.video.presentation.state.SettingsState
import com.chrynan.video.presentation.presenter.SettingsPresenter
import com.chrynan.video.ui.adapter.factory.SettingsAdapterFactory
import com.chrynan.video.ui.adapter.factory.bindAdapterFactory
import com.chrynan.video.ui.adapter.settings.SettingsItemAdapter
import com.chrynan.video.viewmodel.SettingsItemViewModel
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsFragment :
    BaseFragment<SettingsIntent, SettingsState, SettingsChange, SettingsScreen>(),
    SettingsItemAdapter.SettingsItemSelectedListener {

    companion object {

        fun newInstance() = SettingsFragment()
    }

    @Inject
    override lateinit var presenter: SettingsPresenter

    @Inject
    lateinit var adapterFactory: SettingsAdapterFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsRecyclerView?.bindAdapterFactory(adapterFactory)
    }

    override fun intents(): Flow<SettingsIntent> {
        TODO("Not yet implemented")
    }

    override fun render(state: SettingsState) {
        TODO("Not yet implemented")
    }

    override fun goTo(screen: SettingsScreen) {
        TODO("Not yet implemented")
    }

    override fun onSettingsItemSelected(type: SettingsItemViewModel.SettingsType) {

    }
}