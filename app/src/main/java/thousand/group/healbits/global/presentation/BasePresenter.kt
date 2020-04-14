package thousand.group.healbits.global.presentation

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Parcelable
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import thousand.group.healbits.global.receivers.GlobalReceiver
import java.io.Serializable

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    companion object {
        internal val TAG = "BasePresenter"
    }

    abstract val context: Context

    private var localBroadcastManager: LocalBroadcastManager? = null
    private var connManager: ConnectivityManager? = null
    private var internetAccess = false

    private val compositeDisposable = CompositeDisposable()
    private val globalReceiver: GlobalReceiver = GlobalReceiver()

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    private val networkListener = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            internetSuccess()
            internetAccess = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            internetError()
            internetAccess = false
        }

        override fun onUnavailable() {
            super.onUnavailable()
            internetError()
            internetAccess = false
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onStart()
        addConnectionCallback()
    }

    override fun onDestroy() {
        onFinish()
        compositeDisposable.dispose()
        unRegisterGlobalReceiver()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }

    protected fun unRegisterGlobalReceiver() {
        context.apply {
            LocalBroadcastManager
                .getInstance(applicationContext)
                .unregisterReceiver(globalReceiver)
        }
    }

    protected fun registerGlobalReceiver(vararg actionTextList: String) {

        val filter = IntentFilter()

        actionTextList.forEach {
            filter.addAction(it)
        }

        context.apply {
            LocalBroadcastManager.getInstance(applicationContext).registerReceiver(
                globalReceiver,
                filter
            )
        }
    }

    protected fun <T> sendMessageReceiver(action: String, title: String?, message: T?) {

        Log.i(TAG, localBroadcastManager.toString())
        context.apply {

            if (localBroadcastManager == null) {
                localBroadcastManager = LocalBroadcastManager.getInstance(applicationContext)
            }

            localBroadcastManager?.apply {

                val localIntent = Intent(action)

                title?.apply {
                    message?.apply {

                        when (this) {
                            is Parcelable -> {
                                localIntent.putExtra(title, message as Parcelable)
                            }
                            is String -> {
                                localIntent.putExtra(title, message as String)
                            }
                            is Boolean -> {
                                localIntent.putExtra(title, message as Boolean)
                            }
                            is Int -> {
                                localIntent.putExtra(title, message as Int)
                            }
                            is ArrayList<*> -> {
                                localIntent.putExtra(title, message as ArrayList<*>)
                            }
                            is Serializable -> {
                                localIntent.putExtra(title, message as Serializable)
                            }
                        }
                    }
                }

                sendBroadcast(localIntent)
            }
        }
    }

    protected fun getGlobalReceiver(): GlobalReceiver = globalReceiver

    protected fun setGlobalReceiverCallback(onReceivedDataListener: (intent: Intent) -> Unit) {
        getGlobalReceiver().setCallback(onReceivedDataListener)
    }

    protected fun addConnectionCallback() {
        context.apply {
            connManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            connManager?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    registerDefaultNetworkCallback(networkListener)
                } else {
                    registerNetworkCallback(
                        networkRequest,
                        networkListener
                    )
                }
            }
        }
    }

    protected fun unregisterConnectionCallback() {
        try {
            connManager?.apply {
                unregisterNetworkCallback(networkListener)
            }
        } catch (ex: Exception) {
        }
    }

    protected fun isInternetActive() = internetAccess

    abstract fun internetSuccess()

    abstract fun internetError()

    abstract fun onStart()

    abstract fun onFinish()

}