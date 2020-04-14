package thousand.group.healbits.global.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.setImageUrl(url: String?) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun ImageView.setCirculeImageUrl(url: String?) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .optionalCircleCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)