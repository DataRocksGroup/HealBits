package thousand.group.healbits.global.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise.view.*
import thousand.group.healbits.R
import thousand.group.healbits.model.simple.Exercise

class ExerciseAdapter(
    val context: Context,
    val OnItemClickListener: (position: Int, model: Exercise) -> Unit,
    val onEmptyListener: (show: Boolean) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    private var dataList = mutableListOf<Exercise>()
    private var TAG = "CategoryAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    fun setData(dataList: MutableList<Exercise>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyDataSetChanged()

        onEmptyListener.invoke(this.dataList.isEmpty())
    }

    fun getItem(position: Int): Exercise = dataList[position]

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, model: Exercise) {
            itemView.apply {
                model.apply {

                    title.text = name

                    desc.text = description_text

                    setOnClickListener {
                        OnItemClickListener.invoke(position, model)
                    }
                }
            }
        }
    }
}