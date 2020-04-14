package thousand.group.healbits.global.extentions

import android.content.Context
import thousand.group.healbits.global.base.BaseFragment

val BaseFragment.appContext: Context get() = activity?.applicationContext!!

fun BaseFragment.close() = fragmentManager?.popBackStack()