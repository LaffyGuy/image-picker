package com.project.imagepicker.core.network.api

import com.project.imagepicker.core.network.dto.NetworkImage
import com.project.imagepicker.core.network.dto.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

   @GET("api/")
   suspend fun searchImages(
       @Query("q") query: String,
       @Query("orientation") orientation: String = "vertical",
       @Query("image_type") imageType: String = "photo",
       @Query("per_page") perPage: Int = 20,
       @Query("page") page: Int = 1
   ): NetworkResponse

    @GET("api/")
    suspend fun getImageDetails(
        @Query("id") id: String
    ): NetworkImage


}