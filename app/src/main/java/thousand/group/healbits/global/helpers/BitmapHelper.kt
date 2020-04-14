package thousand.group.healbits.global.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import okhttp3.MultipartBody
import java.io.ByteArrayOutputStream

@SuppressLint("LongLogTag")
object BitmapHelper {

    fun getFileDataFromBitmap(fieldName: String, bitmap: Bitmap): MultipartBody.Part? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)

        val filePart = RequestBodyHelper.getRequestBodyImage(byteArrayOutputStream.toByteArray())

        return RequestBodyHelper.getMultipartData(
            fieldName,
            "${System.currentTimeMillis()}.jpg",
            filePart
        )
    }

    fun getBitmapFromUrl(
        context: Context?,
        url: String,
        image_width: Int,
        image_height: Int,
        imageLoadedListener: (bitmap: Bitmap) -> Unit
    ) {
        context?.apply {
            Glide.with(this)
                .asBitmap()
                .load(url)
                .apply(RequestOptions().override(image_width, image_height))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {}

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        imageLoadedListener.invoke(resource)
                    }
                })
        }
    }

    fun getBitmapFromUri(
        context: Context?,
        uri: Uri,
        image_width: Int,
        image_height: Int,
        imageLoadedListener: (bitmap: Bitmap) -> Unit
    ) {
        context?.apply {
            Glide.with(this)
                .asBitmap()
                .load(uri)
                .apply(RequestOptions().override(image_width, image_height))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {}

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        imageLoadedListener.invoke(resource)
                    }
                })
        }
    }
}