package com.kanulp.esrandroidtest.repository

import com.kanulp.esrandroidtest.data.remote.NearByApiImpl
import com.kanulp.esrandroidtest.data.remote.NearByApiService
import javax.inject.Inject

class NearByRepository @Inject constructor(private val apiService: NearByApiImpl) {

    suspend fun getNearByData() = apiService.getNearByData()
}