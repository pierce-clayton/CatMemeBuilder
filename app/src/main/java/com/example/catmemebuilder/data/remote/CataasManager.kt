package com.example.catmemebuilder.data.remote

import com.example.catmemebuilder.data.remote.api.CataasService
import com.example.catmemebuilder.data.remote.models.CataasResponse
import retrofit2.Response

object CataasManager {
    private val service : CataasService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(CataasService::class.java)
    }

    suspend fun getImage() : Response<CataasResponse> {
        return service.getImage()
    }

}