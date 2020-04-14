package thousand.group.healbits.global.helpers

import android.util.Log
import androidx.annotation.ColorRes
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

data class MainFragmentHelper(
    var title: String = "",
    var isShowBottomNavBar: Boolean = true,
    var posBottomNav: Int? = null,
    @ColorRes var statusBarColorRes: Int? = null
) {
    companion object {

        internal val TAG = MainFragmentHelper::class.java.simpleName

        fun getJsonFragmentTag(fragmentHelper: MainFragmentHelper): String =
            Gson().toJson(fragmentHelper).toString()

        fun isShowBottomNavBar(fragmentTag: String?): Boolean {
            Log.i(TAG, "isShowBottomNavBar -> $fragmentTag")
            return try {
                if (fragmentTag != null)
                    Gson().fromJson(fragmentTag, MainFragmentHelper::class.java).isShowBottomNavBar
                else
                    false
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                true
            }
        }

        fun getBottomNavPos(fragmentTag: String?): Int? {
            Log.i(TAG, "getBottomNavPos -> $fragmentTag")

            return try {
                if (fragmentTag != null) {
                    Gson().fromJson(fragmentTag, MainFragmentHelper::class.java).posBottomNav
                } else {
                    null
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }

        fun getStatusBarColorRes(fragmentTag: String?): Int? {
            Log.i(TAG, "getBottomNavPos -> $fragmentTag")

            return try {
                if (fragmentTag != null) {
                    Gson().fromJson(fragmentTag, MainFragmentHelper::class.java).statusBarColorRes
                } else {
                    null
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                null
            }
        }

    }
}