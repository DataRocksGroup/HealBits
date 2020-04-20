package thousand.group.healbits.model.simple

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var password: String,
    var first_name: String,
    var last_name: String,
    var gender: String,
    var height: Int,
    var weight: Int,
    var id: Int,
    var phone: Int
) : Parcelable