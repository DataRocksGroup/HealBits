package thousand.group.healbits.model.simple

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exercise(
    var id: Number,
    var name: String,
    var description_text: String,
    var youtube_url: String?
) : Parcelable