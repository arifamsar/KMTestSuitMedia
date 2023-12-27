package com.arfsar.kmtestsuitmedia.data.retrofit.api

import com.arfsar.kmtestsuitmedia.data.retrofit.response.SingleUserResponse
import com.arfsar.kmtestsuitmedia.data.retrofit.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UserResponse

    @GET("users/{id}")
    suspend fun detailUser(
        @Path("id") id: Int
    ): SingleUserResponse
}