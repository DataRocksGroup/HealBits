package thousand.group.mybuh.global.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalListItemDecoration(private var space: Int, private var size: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if (parent.getChildAdapterPosition(view) != 0 || parent.getChildAdapterPosition(view) != size - 1) {
            outRect.top = space
            outRect.bottom = space

        } else {
            outRect.setEmpty()
        }

    }
}