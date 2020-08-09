package com.chrynan.video.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.chrynan.video.R
import com.chrynan.video.ui.view.SnackbarView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
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

data class TextChange(
    val charSequence: CharSequence? = null,
    val start: Int = 0,
    val before: Int = 0,
    val count: Int = charSequence?.length ?: 0
)

fun TextView?.textChanges(): Flow<TextChange?> {
    if (this == null) return emptyFlow()

    val mutableStateFlow = MutableStateFlow(TextChange(charSequence = text))

    val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            mutableStateFlow.value =
                TextChange(charSequence = s, start = start, count = count, before = before)
        }
    }

    addTextChangedListener(textWatcher)

    return mutableStateFlow
        .onCompletion { removeTextChangedListener(textWatcher) }
}

fun TextView?.onEnterPressed(action: () -> Unit) {
    this?.setOnKeyListener(object : View.OnKeyListener {
        override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
            if (event.action == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER)) {
                action()

                return true
            }

            return false
        }
    })
}

fun View.showKeyboard() {
    requestFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}
