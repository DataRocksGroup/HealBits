package thousand.group.healbits.global.extentions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

internal fun FragmentManager.removeFragment(
    tag: String,
    animateEnter: Int? = null,
    animateExit: Int? = null
) {
    this.findFragmentByTag(tag)?.let {
        val tr = this.beginTransaction()

        if (animateEnter != null && animateExit != null) {
            tr.setCustomAnimations(animateEnter, animateExit)
        }

        tr.remove(it)
            .commit()
        this.popBackStack()
    }
}

internal fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    animateEnter: Int? = null,
    animateExit: Int? = null
) {
    val tr = this.beginTransaction()

    if (animateEnter != null && animateExit != null) {
        tr.setCustomAnimations(animateEnter, animateExit)
    }
    tr.disallowAddToBackStack()
        .add(containerViewId, fragment, tag)
        .commit()
}

internal fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    animateEnter: Int? = null,
    animateExit: Int? = null
) {
    val tr = this.beginTransaction()

    if (animateEnter != null && animateExit != null) {
        tr.setCustomAnimations(animateEnter, animateExit)
    }
    tr.disallowAddToBackStack()
        .replace(containerViewId, fragment, tag)
        .commitAllowingStateLoss()
}

internal fun FragmentManager.replaceFragmentWithBackStack(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    animateEnter: Int? = null,
    animateExit: Int? = null
) {
    val tr = this.beginTransaction()

    if (animateEnter != null && animateExit != null) {
        tr.setCustomAnimations(animateEnter, animateExit)
    }

    tr.replace(containerViewId, fragment, tag)
        .addToBackStack(tag)
        .commitAllowingStateLoss()
}

internal fun FragmentManager.addFragmentWithBackStack(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    animateEnter: Int? = null,
    animateExit: Int? = null
) {
    val tr = this.beginTransaction()

    if (animateEnter != null && animateExit != null) {
        tr.setCustomAnimations(animateEnter, animateExit)
    }

    tr.add(containerViewId, fragment, tag)
        .addToBackStack(tag)
        .commitAllowingStateLoss()
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

internal fun FragmentManager.clearBackStack() {
    if (this.backStackEntryCount > 0) {
        this.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

internal fun FragmentManager.clearAndReplaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    animateEnter: Int? = null,
    animateExit: Int? = null
) {
    this.clearBackStack()

    val tr = this.beginTransaction()

    if (animateEnter != null && animateExit != null) {
        tr.setCustomAnimations(animateEnter, animateExit)
    }

    tr.disallowAddToBackStack()
        .replace(containerViewId, fragment, tag)
        .commitAllowingStateLoss()
}


internal fun FragmentManager.getCallerFragment(): String? = fragments.last().tag

internal fun FragmentManager.findFragment(tag: String): Fragment? = findFragmentByTag(tag)