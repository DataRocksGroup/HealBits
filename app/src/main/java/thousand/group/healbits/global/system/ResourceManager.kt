package thousand.group.healbits.global.system

import android.content.Context
import androidx.annotation.*
import androidx.core.content.ContextCompat

class ResourceManager(private val context: Context) {

    fun getString(@StringRes id: Int) = context.getString(id)

    fun getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)

    fun getDimension(@DimenRes id: Int) = context.resources.getDimension(id)

    fun getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)

    fun getArray(@ArrayRes id: Int) = context.resources.obtainTypedArray(id)
}