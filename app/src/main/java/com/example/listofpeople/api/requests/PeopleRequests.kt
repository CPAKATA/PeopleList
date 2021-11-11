package com.example.listofpeople.api.requests

import com.example.listofpeople.api.dto.People
import retrofit2.Call
import retrofit2.http.GET

interface PeopleRequests {
    @GET("users?page=2")
    suspend fun getPeople(): People
}