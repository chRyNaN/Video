package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.chrynan.video.R
import kotlinx.android.synthetic.main.widget_search.view.*

class SearchWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_search, this)

        orientation = VERTICAL

        searchWidgetEditText?.setBackgroundShape(BackgroundShape.Round)
    }
}