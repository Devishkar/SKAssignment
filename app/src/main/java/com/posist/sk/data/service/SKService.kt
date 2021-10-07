package com.posist.sk.data.service
import com.posist.sk.data.SKData
import retrofit2.Response
import retrofit2.http.GET

interface SKService {

    @GET("app-sample/v1.json")
    suspend fun getSKData(): Response<SKData>
}