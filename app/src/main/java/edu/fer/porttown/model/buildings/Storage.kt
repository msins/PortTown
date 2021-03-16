package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.Building
import edu.fer.porttown.model.Resource

class Storage(level: Int) : StorageBuilding(level) {
    override fun stores(): List<Resource.Type> = Resource.Type.values().toList()

    override fun storageSize(): Long {
        TODO("Not yet implemented")
    }

    override fun getType(): Building.Type = Building.Type.STORAGE
    override fun getResourceType(): Resource.Type? = null
}