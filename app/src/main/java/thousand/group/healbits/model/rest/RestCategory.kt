package thousand.group.healbits.model.rest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import thousand.group.healbits.model.simple.Category

@Parcelize
data class RestCategory(
    val items: MutableList<Category>?
) : Parcelable