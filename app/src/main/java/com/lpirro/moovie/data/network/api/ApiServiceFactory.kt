package com.lpirro.moovie.data.network.api

import com.google.gson.*
import com.lpirro.moovie.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ApiServiceFactory {

    const val BASE_URL = BuildConfig.BASE_URL

    fun makeApiService(): ApiService {
        return makeApiService(makeOkHttpClient())
    }

    private fun makeApiService(okHttpClient: OkHttpClient): ApiService {

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(makeGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(ApiService::class.java)
    }

    private fun makeOkHttpClient() : OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.retryOnConnectionFailure(false)
                .addNetworkInterceptor(makeLoggingInterceptor())

        return okHttpClientBuilder.build()

    }

    private fun makeGson() = GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .serializeNulls()
                .create()

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE

        return loggingInterceptor
    }


    private class DateDeserializer : JsonDeserializer<Date> {
        val DATE_FORMATS = arrayOf(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd - HH:mm:ss",
                "yyyy-MM-dd", "dd/MM/yyyy")

        @Throws(JsonParseException::class)
        override fun deserialize(jsonElement: JsonElement, typeOF: Type,
                                 context: JsonDeserializationContext): Date {
            for (format in DATE_FORMATS) {
                try {
                    return SimpleDateFormat(format, Locale.US).parse(jsonElement.asString)
                } catch (e: ParseException) {
                }

            }
            throw JsonParseException("Unparseable date: \"" + jsonElement.asString
                    + "\". Supported formats: " + Arrays.toString(DATE_FORMATS))
        }
    }
}
