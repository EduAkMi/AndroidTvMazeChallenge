package com.jobsitychallenge.api

import com.jobsitychallenge.utils.Constants.Companion.HEADER_CONTENT_KEY
import com.jobsitychallenge.utils.Constants.Companion.HEADER_CONTENT_VALUE
import com.jobsitychallenge.utils.Constants.Companion.MAIN_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIService {
    val tvMazeAPI: ApiTvMaze = getTvMazeAPIClient().create(ApiTvMaze::class.java)

    private fun getTvMazeAPIClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .client(getInterceptorClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val headers = chain.request().newBuilder()
                    .addHeader(HEADER_CONTENT_KEY, HEADER_CONTENT_VALUE)
                val newRequest = headers.build()
                chain.proceed(newRequest)
            }
        return interceptor.build()
    }
}