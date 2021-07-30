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

    suspend fun getImage(filter: String) : Response<CataasResponse> {
        val response = service.getImage(filter)
        return response
    }
    suspend fun getGif(filter: String) : Response<CataasResponse> {
        return service.getGif(filter)
    }
    suspend fun getTextImage(text: String, color: String, size: String, filter: String) : Response<CataasResponse> {
        return service.getTextImage(text, color, size, filter)
    }
    suspend fun getTextGif(text: String, color: String, size: String, filter: String) : Response<CataasResponse> {
        return service.getTextGif(text, color, size, filter)
    }

}