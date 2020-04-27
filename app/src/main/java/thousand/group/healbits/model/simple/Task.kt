package thousand.group.healbits.model.simple

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task(
    var id: Int,
    var user_id: Int,
    var text_name: String,
    var status: Int,
    var creation_date: String,
    var isOpened: Boolean = false
) : Parcelable