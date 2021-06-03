package com.kanulp.esrandroidtest.data.remote

import com.kanulp.esrandroidtest.data.model.NearByModel
import retrofit2.Response
import retrofit2.http.GET

interface NearByApiService {

    @GET("nearby?lat=52.152803&lng=9.9417843")
    suspend fun getNearByData(
    ): Response<NearByModel>
}