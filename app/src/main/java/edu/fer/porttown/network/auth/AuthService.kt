package edu.fer.porttown.network.auth

import edu.fer.porttown.network.dto.AuthDtos
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun login(
        @Body loginDto: AuthDtos.LoginDto,
        @Header("Authorization") password: String
    ): Response<AuthDtos.VerifyUser>

    @POST("user/check-availability")
    suspend fun checkAvailability(
        @Body availability: AuthDtos.AvailabilityDto
    ): Response<Boolean>

    @Headers("Content-Type: application/json")
    @POST("user/register/{townName}")
    suspend fun register(
        @Path(value = "townName") townName: String,
        @Body userDto: AuthDtos.UserDto
    ): Response<AuthDtos.VerifyUser>
}