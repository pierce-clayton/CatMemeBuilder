package com.example.catmemebuilder.data.remote.api

import com.example.catmemebuilder.data.remote.models.CataasResponse
import com.example.catmemebuilder.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CataasService {

    @GET(Constants.IMAGE_ENDPOINT)
    suspend fun getImage(
        @Query("filter")filter: String,
        @Query("json")json: String = "true"
    ) : Response<CataasResponse>

    @GET(Constants.GIF_ENDPOINT)
    suspend fun getGif(
        @Query("filter")filter: String,
        @Query("json")json: String = "true"
    ) : Response<CataasResponse>

    @GET(Constants.TEXT_IMAGE_ENDPOINT)
    suspend fun getTextImage(
        @Path("text")text: String,
        @Query("color")color: String,
        @Query("size")size: String,
        @Query("filter")filter: String,
        @Query("json")json: String = "true"
    ) : Response<CataasResponse>

    @GET(Constants.TEXT_GIF_ENDPOINT)
    suspend fun getTextGif(
        @Path("text")text: String,
        @Query("color")color: String,
        @Query("size")size: String,
        @Query("filter")filter: String,
        @Query("json")json: String = "true"
    ) : Response<CataasResponse>
}