package edu.fer.porttown.network.dto

import com.google.gson.annotations.SerializedName
import edu.fer.porttown.model.Town

sealed class AuthDtos {

    data class VerifyUser(
        @SerializedName("id") val id: String,
        @SerializedName("username") val username: String,
        @SerializedName("email") val email: String,
        @SerializedName("town") val townDto: TownDto,
        @SerializedName("token") val token: String
    )

    data class LoginDto(
        @SerializedName("username") val username: String
    )

    data class AvailabilityDto(
        @SerializedName("availability") val availability: Boolean
    )

    /**
     * Ne treba productions i storages buildings...
     */
    data class TownDto(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("level") val level: Int,
        @SerializedName("buildings") val buildings: List<String>,
        @SerializedName("items") val items: List<String>,
    ) {
        fun toTown(): Town {
            return Town(id, name, level, buildings, items)
        }
    }

    data class UserDto(
        @SerializedName("username") var username: String,
        @SerializedName("password") var password: String,
        @SerializedName("email") var email: String
    )
}