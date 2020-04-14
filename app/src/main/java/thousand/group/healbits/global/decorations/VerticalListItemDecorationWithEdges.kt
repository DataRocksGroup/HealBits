package thousand.group.mybuh.global.decorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class VerticalListItemDecorationWithEdges(
    val context: Context,
    val childCount: Int,
    @DrawableRes val drawableRes: Int
) : RecyclerView.ItemDecoration() {
    val divider: Drawable?

    init {
        divider = ContextCompat.getDrawable(context, drawableRes)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingLeft

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            divider?.apply {
                val top = child.bottom + params.bottomMargin
                val bottom = top + intrinsicHeight

                setBounds(left, top, right, bottom)
                draw(c)
            }
        }
    }
}