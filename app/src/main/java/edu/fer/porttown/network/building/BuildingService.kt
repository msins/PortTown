package edu.fer.porttown.network.building

import edu.fer.porttown.model.Building
import edu.fer.porttown.network.dto.BuildingDtos
import edu.fer.porttown.util.Api
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BuildingService {

    @PUT("${Api.BASE_URL}/{accountId}/buildings/{building}")
    suspend fun upgradeBuilding(
        @Header("Authorization") token: String,
        @Path(value = "accountId") accountId: String,
        @Path(value = "building") building: Building.Type
    ): Response<BuildingDtos.UpgradeBuilding>

    @POST("${Api.BASE_URL}/{accountId}/buildings/{building}")
    suspend fun buildBuilding(
        @Header("Authorization") token: String,
        @Path(value = "accountId") accountId: String,
        @Path(value = "building") building: Building.Type
    ): Response<BuildingDtos.UpgradeBuilding>
}