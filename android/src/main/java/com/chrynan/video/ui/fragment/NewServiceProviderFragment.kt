package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.video.R
import com.chrynan.video.navigator.NewServiceProviderNavigator
import com.chrynan.video.presenter.NewServiceProviderPresenter
import com.chrynan.video.ui.view.NewServiceProviderView
import javax.inject.Inject

class NewServiceProviderFragment : BaseFragment(),
    NewServiceProviderView,
    NewServiceProviderNavigator {

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

    }
}