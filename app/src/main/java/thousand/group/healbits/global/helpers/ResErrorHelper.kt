package thousand.group.healbits.global.helpers

import android.util.Log
import com.google.gson.JsonParser
import retrofit2.HttpException
import retrofit2.Response
import thousand.group.healbits.R
import thousand.group.healbits.global.constants.simple.AppConstants
import thousand.group.healbits.global.system.ResourceManager

object ResErrorHelper {
    internal fun showThrowableMessage(
        resourceManager: ResourceManager,
        tag: String,
        throwable: Throwable
    ): String = when (throwable) {
        is HttpException -> {
            val body = throwable.response()?.errorBody()
            var mess = ""

            body?.string()?.apply {
                val message = GsonHelper.getExceptionMessage(this)
                Log.i(tag, message)
                Log.i(tag, "HttpException")
                mess = message
            }

            mess
        }
        else -> {
            Log.i(tag, throwable.localizedMessage.toString())
            resourceManager.getString(R.string.message_error_internet_connection)
        }
    }

    internal fun <T> showErrorMessage(tag: String, response: Response<T>): String {
        val message = JsonParser()
            .parse(
                response.errorBody()!!
                    .string()
            )
            .asJsonObject
            .get(AppConstants.MESSAGE)
            .asString

        Log.i(tag, message)
        return message
    }

}