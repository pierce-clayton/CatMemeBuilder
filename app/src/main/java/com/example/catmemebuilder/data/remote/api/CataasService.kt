package com.example.catmemebuilder.data.remote.api

import com.example.catmemebuilder.data.remote.models.CataasResponse
import com.example.catmemebuilder.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface CataasService {

    @GET(Constants.IMAGE_ENDPOINT)
    suspend fun getImage() : Response<CataasResponse>
}