package thousand.group.healbits.model.rest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import thousand.group.healbits.model.simple.Exercise

@Parcelize
data class RestExercise(
    val items: MutableList<Exercise>?
) : Parcelable