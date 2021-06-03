package com.kanulp.esrandroidtest.data.remote

import com.kanulp.esrandroidtest.data.model.NearByModel
import retrofit2.Response
import javax.inject.Inject


class NearByApiImpl @Inject constructor(private val apiService: NearByApiService):BaseDataSource(){
    suspend fun getNearByData() = getResult { apiService.getNearByData() }
}