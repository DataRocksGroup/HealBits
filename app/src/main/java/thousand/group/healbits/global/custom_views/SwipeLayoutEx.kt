package thousand.group.healbits.global.custom_views

import android.content.Context
import android.util.AttributeSet
import com.daimajia.swipe.SwipeLayout

open class SwipeLayoutEx : com.daimajia.swipe.SwipeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private val mSwipeListener = SwipeListenerBase()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.addSwipeListener(mSwipeListener)
    }

    override fun onDetachedFromWindow() {
        this.removeSwipeListener(mSwipeListener)
        super.onDetachedFromWindow()
    }

    inner class SwipeListenerBase : SwipeLayout.SwipeListener {
        override fun onOpen(layout: SwipeLayout) {
            mOnSwipeOpen?.invoke(layout)
        }

        override fun onUpdate(layout: SwipeLayout, leftOffset: Int, topOffset: Int) {
            mOnSwipeUpdate?.invoke(layout, leftOffset, topOffset)
        }

        override fun onStartOpen(layout: SwipeLayout?) {
            mOnSwipeStartOpen?.invoke(layout)
        }

        override fun onStartClose(layout: SwipeLayout?) {
            mOnSwipeStartClose?.invoke(layout)
        }

        override fun onHandRelease(layout: SwipeLayout?, xvel: Float, yvel: Float) {
            mOnSwipeHandRelease?.invoke(layout, xvel, yvel)
        }

        override fun onClose(layout: SwipeLayout?) {
            mOnSwipeClose?.invoke(layout)
        }
    }

    private var mOnSwipeOpen: ((layout: SwipeLayout?) -> Unit)? = null
    private var mOnSwipeClose: ((layout: SwipeLayout?) -> Unit)? = null
    private var mOnSwipeStartOpen: ((layout: SwipeLayout?) -> Unit)? = null
    private var mOnSwipeStartClose: ((layout: SwipeLayout?) -> Unit)? = null
    private var mOnSwipeHandRelease: ((layout: SwipeLayout?, xvel: Float, yvel: Float) -> Unit)? =
        null
    private var mOnSwipeUpdate: ((layout: SwipeLayout, leftOffset: Int, topOffset: Int) -> Unit)? =
        null

    fun onSwipeOpen(f: (layout: SwipeLayout?) -> Unit): SwipeLayoutEx {
        mOnSwipeOpen = f
        return this
    }

    fun onSwipeClose(f: (layout: SwipeLayout?) -> Unit): SwipeLayoutEx {
        mOnSwipeClose = f
        return this
    }

    fun onSwipeStartOpen(f: (layout: SwipeLayout?) -> Unit): SwipeLayoutEx {
        mOnSwipeStartOpen = f
        return this
    }

    fun onSwipeStartClose(f: (layout: SwipeLayout?) -> Unit): SwipeLayoutEx {
        mOnSwipeStartClose = f
        return this
    }

    fun onSwipeHandRelease(f: (layout: SwipeLayout?, xvel: Float, yvel: Float) -> Unit): SwipeLayoutEx {
        mOnSwipeHandRelease = f
        return this
    }

    fun onSwipeUpdate(f: (layout: SwipeLayout, leftOffset: Int, topOffset: Int) -> Unit): SwipeLayoutEx {
        mOnSwipeUpdate = f
        return this
    }
}