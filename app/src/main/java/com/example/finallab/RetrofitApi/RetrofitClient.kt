package com.example.finallab.RetrofitApi


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://192.168.0.12:80/"   // I use local XAMPP server, so I change this one to my local laptop ip every time
    private val okHttpClient = OkHttpClient.Builder()
                                            .addInterceptor { chain ->
                                                val original = chain.request()

                                                val requestBuilder = original.newBuilder()
                                                                            .method(original.method(), original.body())

                                                val request = requestBuilder.build()
                                                chain.proceed(request)
                                            }.build()
    val instance: Api by lazy{
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .client(okHttpClient)
                                         .build()

                retrofit.create(Api::class.java)
    }

    fun getBaseUrl(): String{
        return BASE_URL
    }

}