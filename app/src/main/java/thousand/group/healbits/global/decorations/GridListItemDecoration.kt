package thousand.group.mybuh.global.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridListItemDecoration(private var space: Int, private var spanCount: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        with(outRect) {
            left = if (column == 0) 0 else space / 2
            right = if (column == spanCount.dec()) 0 else space / 2
            top = if (position < spanCount) 0 else space
        }
    }
}