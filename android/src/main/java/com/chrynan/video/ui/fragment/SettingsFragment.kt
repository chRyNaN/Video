package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.ui.view.SettingsView
import com.chrynan.video.R
import com.chrynan.video.di.qualifier.SettingsQualifier
import com.chrynan.video.model.ServiceProviderScreen
import com.chrynan.video.navigator.SettingsNavigator
import com.chrynan.video.presenter.SettingsPresenter
import com.chrynan.video.ui.activity.ServiceProviderActivity
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.decorator.SettingsListDecorator
import com.chrynan.video.ui.adapter.settings.SettingsItemAdapter
import com.chrynan.video.viewmodel.SettingsItemViewModel
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : BaseFragment(),
    SettingsView,
    SettingsNavigator,
    SettingsItemAdapter.SettingsItemSelectedListener {

    companion object {

        fun newInstance() = SettingsFragment()
    }

    @Inject
    override lateinit var presenter: SettingsPresenter

    @Inject
    @field:SettingsQualifier.Adapter
    lateinit var managerAdapter: RecyclerViewAdapter

    @Inject
    @field:SettingsQualifier.LayoutManager
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    @field:SettingsQualifier.Decorator
    lateinit var decorator: SettingsListDecorator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsRecyclerView?.apply {
            adapter = managerAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(decorator)
        }

        presenter.getSettings()
    }

    override fun goToServiceProviderList() =
        startActivitySafely { ServiceProviderActivity.newIntent(it, ServiceProviderScreen.List) }

    override fun goToAddNewServiceProvider() =
        startActivitySafely { ServiceProviderActivity.newIntent(it, ServiceProviderScreen.New) }

    override fun onSettingsItemSelected(type: SettingsItemViewModel.SettingsType) {
        if (type == SettingsItemViewModel.SettingsType.SERVICES) goToServiceProviderList()
    }
}