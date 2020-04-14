package thousand.group.mybuh.global.extentions

import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.uncheckAllItems() {
    menu.setGroupCheckable(0, true, false)
    for (i in 0 until menu.size()) {
        menu.getItem(i).isChecked = false
    }
    menu.setGroupCheckable(0, true, true)
}

fun BottomNavigationView.setChecked(position: Int) {
    uncheckAllItems()

    menu.setGroupCheckable(0, true, false)
    try {
        menu.getItem(position).isChecked = true
    } catch (ex: Exception) {

    }
    menu.setGroupCheckable(0, true, true)
}