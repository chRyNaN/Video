package com.chrynan.video.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseManagerAdapter
import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import com.chrynan.video.ui.adapter.position.GridLayoutPositionManager
import com.chrynan.video.ui.adapter.position.LinearLayoutPositionManager
import com.chrynan.video.ui.view.SnackbarView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.*
import reactivecircus.flowbinding.recyclerview.scrollEvents

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

val RecyclerView.positionManager: AdapterPositionManager?
    get() = when (val lm = layoutManager) {
        is LinearLayoutManager -> LinearLayoutPositionManager(lm)
        is GridLayoutManager -> GridLayoutPositionManager(lm)
        else -> null
    }

enum class EndlessScrollDirection {

    TOP,
    BOTTOM
}

data class LoadMoreEvent(
    val view: RecyclerView?,
    val currentItemCount: Int,
    val direction: EndlessScrollDirection,
    val threshold: Int,
    val firstVisibleItemPosition: Int,
    val lastVisibleItemPosition: Int
)

fun RecyclerView.loadMoreEvents(
    threshold: Int = 4,
    direction: EndlessScrollDirection = EndlessScrollDirection.BOTTOM
): Flow<LoadMoreEvent> {
    var previousFirstVisibleItemPosition = -1
    var previousLastVisibleItemPosition = -1
    var isLoading = false
    var itemCountBeforeLoading = adapter?.itemCount ?: 0

    return scrollEvents().mapNotNull {
        val pm = positionManager
        val ma = adapter as? BaseManagerAdapter<*>

        if (pm != null && ma != null) {
            val firstPosition = pm.findFirstVisibleItemPosition()
            val lastPosition = pm.findLastVisibleItemPosition()

            if (firstPosition != previousFirstVisibleItemPosition || lastPosition != previousLastVisibleItemPosition) {
                if (itemCountBeforeLoading != ma.itemCount) {
                    itemCountBeforeLoading = ma.itemCount
                    isLoading = false
                }

                if (!isLoading) {
                    previousFirstVisibleItemPosition = firstPosition
                    previousLastVisibleItemPosition = lastPosition

                    val firstWithinThreshold = firstPosition - threshold <= 0
                    val lastWithinThreshold =
                        lastPosition + threshold >= ma.items.lastIndex

                    if ((direction == EndlessScrollDirection.TOP && firstWithinThreshold) or
                        (direction == EndlessScrollDirection.BOTTOM && lastWithinThreshold)
                    ) {
                        isLoading = true
                        itemCountBeforeLoading = ma.itemCount

                        LoadMoreEvent(
                            view = it.view,
                            currentItemCount = itemCountBeforeLoading,
                            direction = direction,
                            threshold = threshold,
                            firstVisibleItemPosition = firstPosition,
                            lastVisibleItemPosition = lastPosition
                        )
                    }
                }
            }
        }

        null
    }
}