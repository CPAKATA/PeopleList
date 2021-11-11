package com.example.listofpeople.api

import com.example.listofpeople.api.requests.PeopleRequests
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private val OkHttpClient = OkHttpClient()
        .newBuilder()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.build())
        .build()

    val people = retrofit.create(PeopleRequests::class.java)
}