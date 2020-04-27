package thousand.group.healbits.model.rest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import thousand.group.healbits.model.simple.Task

@Parcelize
data class RestTask(
    val items: MutableList<Task>?
) : Parcelable