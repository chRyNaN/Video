package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.common.utils.filterUntilFirstChange
import com.chrynan.logger.Logger
import com.chrynan.video.R
import com.chrynan.video.navigator.NewServiceProviderNavigator
import com.chrynan.video.presenter.NewServiceProviderPresenter
import com.chrynan.video.ui.view.NewServiceProviderView
import com.chrynan.video.utils.onEnterPressed
import com.chrynan.video.utils.textChanges
import kotlinx.android.synthetic.main.fragment_new_service_provider.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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

        newServiceProviderMainActionButton?.setOnClickListener {
            presenter.handleProviderUriTest(
                providerUri = newServiceProviderTextInputEditText?.text?.toString()
            )
        }

        newServiceProviderTextInputEditText?.onEnterPressed {
            presenter.handleProviderUriTest(
                providerUri = newServiceProviderTextInputEditText?.text?.toString()
            )
        }

        newServiceProviderTextInputEditText.textChanges()
            .filterUntilFirstChange()
            .map { it?.charSequence?.toString() }
            .onEach { presenter.handleProviderUriStringChange(providerUri = it) }
            .catch { Logger.logError(throwable = it, message = "Error listening to text changes.") }
            .launchIn(this)
    }

    override fun showProviderUriValid() {
        newServiceProviderTextInputLayout?.error = null
        newServiceProviderMainActionButton?.isEnabled = true
    }

    override fun showProviderUriInvalid(errorText: String) {
        newServiceProviderTextInputLayout?.error = errorText
        newServiceProviderMainActionButton?.isEnabled = false
    }
}