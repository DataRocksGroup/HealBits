package thousand.group.healbits.global.helpers

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object GsonHelper {
    internal fun getType(cll: Class<*>, param: Class<*>): Type {
        return object : ParameterizedType {
            override fun getRawType(): Type {
                return cll
            }

            override fun getOwnerType(): Type? {
                return null
            }

            override fun getActualTypeArguments(): Array<Type> {
                return Array(1) { param }
            }

        }
    }

    internal fun <T> getJsonFromObject(model: T): String {
        return Gson().toJson(model)
    }

    internal fun <T> getObjectFromJson(jsonObject: JsonObject, clazz: Class<T>): T {
        return Gson().fromJson(jsonObject, clazz)
    }

    internal fun <T> getObjectFromString(modelString: String, clazz: Class<T>): T {
        return Gson().fromJson(modelString, clazz)
    }

    internal fun getExceptionMessage(modelString: String): String {
        return JsonParser().parse(modelString).asJsonObject.get("message").asString
    }


}