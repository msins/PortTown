package edu.fer.porttown.network.dto

import edu.fer.porttown.model.Building

sealed class BuildingDtos {
    data class UpgradeBuilding(
        val building: Building.Type,
        val level: Int,
        val rps: Long
    )
}