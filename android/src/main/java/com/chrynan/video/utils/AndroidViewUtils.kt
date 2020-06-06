package com.chrynan.video.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.chrynan.video.ui.view.SnackbarView
import com.chrynan.video.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onCompletion

fun snackbarOf(
    view: View?,
    message: String,
    type: SnackbarView.Type = SnackbarView.Type.MESSAGE,
    length: SnackbarView.Length = SnackbarView.Length.SHORT,
    action: SnackbarView.Action? = null
): Snackbar? {
    if (view == null) return null

    val duration = when (length) {
        SnackbarView.Length.SHORT -> Snackbar.LENGTH_SHORT
        SnackbarView.Length.LONG -> Snackbar.LENGTH_LONG
        SnackbarView.Length.INDEFINITE -> Snackbar.LENGTH_INDEFINITE
    }

    return Snackbar.make(view, message, duration).apply {
        if (action != null) {
            setAction(action.title) { action.block() }
        }

        setTextColor(view.resources.getColor(R.color.text_primary_color, null))
        setActionTextColor(view.resources.getColor(R.color.text_primary_color, null))

        when (type) {
            SnackbarView.Type.ERROR -> setBackgroundTint(
                view.resources.getColor(
                    R.color.error_color,
                    null
                )
            )
            SnackbarView.Type.MESSAGE -> setBackgroundTint(
                view.resources.getColor(
                    R.color.accent_two_color,
                    null
                )
            )
        }
    }
}

fun TextView.textChanges(): Flow<CharSequence?> {
    val mutableStateFlow = MutableStateFlow(text)

    val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            mutableStateFlow.value = s
        }
    }

    addTextChangedListener(textWatcher)

    return mutableStateFlow
        .onCompletion { removeTextChangedListener(textWatcher) }
}