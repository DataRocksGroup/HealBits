package thousand.group.healbits.model.rest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import thousand.group.healbits.model.simple.User

@Parcelize
data class RestUser(
    val items: MutableList<User>
) : Parcelable