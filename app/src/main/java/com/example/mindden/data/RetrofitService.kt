package com.example.mindden.data

import com.example.mindden.data.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RetrofitService {

    @GET("api/")
    suspend fun listUserData(): RemoteResult

}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}