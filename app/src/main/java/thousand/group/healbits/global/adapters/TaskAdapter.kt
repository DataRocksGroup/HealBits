package thousand.group.healbits.global.adapters

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import kotlinx.android.synthetic.main.item_task.view.*
import thousand.group.healbits.R
import thousand.group.healbits.model.simple.Task

class TaskAdapter(
    val context: Context,
    val itemDeleteClicked: (model: Task, position: Int) -> Unit,
    val itemChangeStatusClicked: (model: Task, position: Int) -> Unit,
    val itemEmptyCallback: (isEmpty: Boolean) -> Unit
) : RecyclerSwipeAdapter<TaskAdapter.ViewHolder>() {

    companion object {
        internal val TAG = "TaskAdapter"
    }

    private val dataList = mutableListOf<Task>()
    private val strikeThroughSpan = StrikethroughSpan()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun getSwipeLayoutResourceId(position: Int): Int = R.id.swipe_main_layout

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mItemManger.bindView(holder.itemView, position)
        holder.bind(getItem(position), position)
    }

    fun setDataList(list: MutableList<Task>) {
        this.dataList.clear()
        this.dataList.addAll(list)

        notifyDataSetChanged()

        itemEmptyCallback.invoke(this.dataList.isEmpty())
    }

    fun getItem(position: Int): Task = dataList[position]

    fun getList(): MutableList<Task> = dataList

    fun addTask(model: Task) {
        dataList.add(model)
        notifyItemInserted(dataList.size - 1)
    }

    fun deleteItem(position: Int) {
        dataList.removeAt(position)

        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataList.size)

        itemEmptyCallback.invoke(this.dataList.isEmpty())

        mItemManger.closeAllItems()
    }

    fun closeBasketItem(position: Int) = mItemManger.closeItem(position)

    fun changeStatusOfText(position: Int, status: Int) {
        getItem(position).status = status
        notifyItemChanged(position)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: Task, position: Int) {
            itemView.apply {

                val spannableString = SpannableString(model.text_name)

                if (model.status == 2) {
                    spannableString.setSpan(
                        strikeThroughSpan,
                        0,
                        spannableString.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                text_task.setText(spannableString, TextView.BufferType.SPANNABLE)

                swipe_main_layout.addDrag(SwipeLayout.DragEdge.Right, bottom_wrapper)

                swipe_main_layout.onSwipeStartOpen {
                    model.isOpened = true
                }

                swipe_main_layout.onSwipeHandRelease { layout, xvel, yvel ->
                    if (model.isOpened) {
                        itemDeleteClicked.invoke(model, position)
                        model.isOpened = false
                    }
                }

                setOnClickListener {
                    itemChangeStatusClicked(model, position)
                }
            }
        }
    }
}
