package thousand.group.healbits.global.extentions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

internal fun Bitmap.compressJPG(quality: Int): Bitmap {
    val stream = ByteArrayOutputStream()

    this.compress(Bitmap.CompressFormat.JPEG, quality, stream)

    val byteArray = stream.toByteArray()

    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

internal fun Bitmap.compressPNG(): Bitmap {
    val stream = ByteArrayOutputStream()

    this.compress(Bitmap.CompressFormat.PNG, 0, stream)

    val byteArray = stream.toByteArray()

    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}