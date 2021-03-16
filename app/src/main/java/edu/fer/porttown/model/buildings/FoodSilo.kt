package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.Building
import edu.fer.porttown.model.Resource

class FoodSilo(level: Int) : StorageBuilding(level) {
    override fun stores(): List<Resource.Type> = listOf(Resource.Type.FOOD)

    override fun storageSize(): Long {
        TODO("Not yet implemented")
    }

    override fun getType(): Building.Type = Building.Type.FOOD_SILO
    override fun getResourceType(): Resource.Type? = null
}