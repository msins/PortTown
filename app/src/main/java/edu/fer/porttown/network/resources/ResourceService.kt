package edu.fer.porttown.network.resources

import edu.fer.porttown.network.dto.ResourceDtos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ResourceService {
    @Headers("Content-Type: application/json")
    @GET("/resources/{townId}")
    suspend fun getResources(
        @Path(value = "townId") townId: String,
        @Header("Authorization") token: String
    ): Response<ResourceDtos.Resources>
}