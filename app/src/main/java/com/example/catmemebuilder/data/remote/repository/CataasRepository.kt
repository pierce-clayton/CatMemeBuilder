package com.example.catmemebuilder.data.remote.repository

import com.example.catmemebuilder.data.remote.CataasManager
import com.example.catmemebuilder.data.remote.api.CataasService
import com.example.catmemebuilder.data.remote.models.CataasResponse
import com.example.catmemebuilder.utils.Resource

object CataasRepository {


    suspend fun getImage() : Resource<CataasResponse> {
        return try{
            val response = CataasManager.getImage()
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