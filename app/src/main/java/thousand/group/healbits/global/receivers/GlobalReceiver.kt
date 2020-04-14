package thousand.group.healbits.global.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class GlobalReceiver : BroadcastReceiver() {

    companion object {
        internal const val LOG_TAG = "GlobalReceiver"
    }

    private lateinit var onReceivedDataListener: (intent: Intent) -> Unit

    override fun onReceive(context: Context?, intent: Intent?) {
        if (::onReceivedDataListener.isInitialized) {
            intent?.apply {
                onReceivedDataListener.invoke(intent)
            }
        }
    }

    fun setCallback(onReceivedDataListener: (intent: Intent) -> Unit) {
        this.onReceivedDataListener = onReceivedDataListener
    }
}