package com.example.catmemebuilder.data.remote.repository

import com.example.catmemebuilder.data.remote.CataasManager
import com.example.catmemebuilder.data.remote.api.CataasService
import com.example.catmemebuilder.data.remote.models.CataasResponse
import com.example.catmemebuilder.utils.Resource

object CataasRepository {


    suspend fun getImage(filter: String) : Resource<CataasResponse> {
        return try{
            val response = CataasManager.getImage(filter)
            if(response.isSuccessful && response.body() != null){
                Resource.Success(response.body()!!)
            }else{
                Resource.Error(null, "No cats found")
            }

        }catch (ex: Exception){
            Resource.Error(null, ex.toString())
        }
    }

    suspend fun getGif(filter: String) : Resource<CataasResponse> {
        return try{
            val response = CataasManager.getGif(filter)
            if(response.isSuccessful && response.body() != null){
                Resource.Success(response.body()!!)
            }else{
                Resource.Error(null, "No cats found")
            }

        }catch (ex: Exception){
            Resource.Error(null, ex.toString())
        }
    }

    suspend fun getTextImage(text: String, color: String, size: String, filter: String) : Resource<CataasResponse> {
        return try{
            val response = CataasManager.getTextImage(text, color, size, filter)
            if(response.isSuccessful && response.body() != null){
                Resource.Success(response.body()!!)
            }else{
                Resource.Error(null, "No cats found")
            }

        }catch (ex: Exception){
            Resource.Error(null, ex.toString())
        }
    }

    suspend fun getTextGif(text: String, color: String, size: String, filter: String) : Resource<CataasResponse> {
        return try{
            val response = CataasManager.getTextGif(text, color, size, filter)
            if(response.isSuccessful && response.body() != null){
                Resource.Success(response.body()!!)
            }else{
                Resource.Error(null, "No cats found")
            }

        }catch (ex: Exception){
            Resource.Error(null, ex.toString())
        }
    }

}