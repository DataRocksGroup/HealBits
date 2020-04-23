package thousand.group.healbits.model.simple

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    var id: Number,
    var name: String
) : Parcelable