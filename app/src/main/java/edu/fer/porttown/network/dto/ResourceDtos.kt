package edu.fer.porttown.network.dto

import com.google.gson.annotations.SerializedName
import edu.fer.porttown.model.Resource

sealed class ResourceDtos {

    data class Resources(
        @SerializedName("resources") val resourceDtos: List<ResourceDto>
    )

    data class ResourceDto(
        @SerializedName("type") val type: Resource.Type,
        @SerializedName("count") val count: Long,
        @SerializedName("rps") val rps: Long
    )

}