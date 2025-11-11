package com.example.sismob.data.remote

import com.example.sismob.data.remote.dto.HeaderData
import com.example.sismob.data.remote.dto.MsgInfo
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {

    @FormUrlEncoded
    @POST("moblogin")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<HeaderData>

    @FormUrlEncoded
    @POST
    suspend fun postData(
        @Url fullUrl: String,
        @Field("querystring") querystring: String
    ): Response<List<MsgInfo>>
}
