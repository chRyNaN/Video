package com.chrynan.video.ui.adapter.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.video.R

abstract class HorizontalDividerListDecorator(context: Context) : RecyclerView.ItemDecoration() {

    companion object {

        private const val FIRST_POSITION = 0
    }

    private val dividerColor by lazy { context.resources.getColor(R.color.divider_color, null) }
    private val dividerHeight by lazy { context.resources.getDimensionPixelSize(R.dimen.divider_height) }

    private val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = dividerColor
        }
    }

    abstract fun shouldDrawTopDividerForViewType(viewType: Int?): Boolean

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.apply {
            canvas.save()

            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val adapterPosition = getChildAdapterPosition(child)
                val adapterViewType = adapter?.getItemViewType(adapterPosition)
                val isAdapterPositionValid =
                    adapterPosition != RecyclerView.NO_POSITION && adapterPosition != FIRST_POSITION
                val shouldDrawTopDivider =
                    isAdapterPositionValid && shouldDrawTopDividerForViewType(adapterViewType)

                if (shouldDrawTopDivider) {
                    val left = paddingLeft.toFloat()
                    val right = width - paddingRight.toFloat()
                    val top = child.top + child.translationY
                    val bottom = top + dividerHeight

                    canvas.drawLine(left, top, right, bottom, paint)
                }
            }

            canvas.restore()
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) = outRect.set(0, dividerHeight, 0, 0)
}