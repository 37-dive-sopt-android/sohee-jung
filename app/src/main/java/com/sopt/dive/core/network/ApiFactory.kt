package com.sopt.dive.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.dive.BuildConfig
import com.sopt.dive.data.auth.repository.AuthRepository
import com.sopt.dive.data.auth.service.AuthService
import com.sopt.dive.data.my.repository.MyRepository
import com.sopt.dive.data.my.service.MyService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.BASE_URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ServicePool {
    val myService: MyService by lazy {
        ApiFactory.create<MyService>()
    }

    val authService: AuthService by lazy {
        ApiFactory.create<AuthService>()
    }
}

object RepositoryPool {
    val authRepository: AuthRepository by lazy {
        AuthRepository(ServicePool.authService)
    }

    val myRepository: MyRepository by lazy {
        MyRepository(ServicePool.myService)
    }
}
