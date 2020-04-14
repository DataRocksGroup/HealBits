package thousand.group.healbits.global.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import thousand.group.healbits.BuildConfig
import thousand.group.healbits.global.di.ServiceProperties.API_FOLDER_URL
import thousand.group.healbits.global.di.ServiceProperties.AUTH_HEADER
import thousand.group.healbits.global.di.ServiceProperties.SERVER_URL
import thousand.group.healbits.global.services.api.ServerService
import thousand.group.healbits.global.services.storage.LocaleStorage

val networkModule = module {
    single { createOkHttpClient() }
    single { createWebService<ServerService>(get(), SERVER_URL + API_FOLDER_URL) }
}

object ServiceProperties {
    const val SERVER_URL = "http://194.4.58.94/"
    const val API_FOLDER_URL = "api/v1/"
    const val AUTH_HEADER = "Authorization"
    const val WEB_SOCKET_URL = "ws://194.4.58.94:8080/"
    const val MAP_URL = "https://maps.googleapis.com/maps/api/directions/"
}

fun createOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()

    okHttpClientBuilder.addInterceptor { chain ->
        var request = chain.request()
        val url = request.url().newBuilder()
        request = request.newBuilder()
            .addHeader(AUTH_HEADER, LocaleStorage.getAccessToken())
            .url(url.build())
            .build()
        chain.proceed(request)
    }

    if (BuildConfig.DEBUG) {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}
