package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.AbstractBuilding
import edu.fer.porttown.model.Resource

abstract class StorageBuilding(level: Int) : AbstractBuilding(level) {
    abstract fun stores(): List<Resource.Type>
    abstract fun storageSize(): Long
}