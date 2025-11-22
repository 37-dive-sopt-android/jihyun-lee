package com.sopt.dive.data.remote.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.dive.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.BASE_URL
    private const val OPEN_API_BASE_URL = "https://reqres.in/api/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val openApiClient = client.newBuilder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("x-api-key", "reqres-free-v1")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    val diveRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val openApiRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(OPEN_API_BASE_URL)
            .client(openApiClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> createDiveService(): T = diveRetrofit.create(T::class.java)
    inline fun <reified T> createOpenApiService(): T = openApiRetrofit.create(T::class.java)
}