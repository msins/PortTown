package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.Building
import edu.fer.porttown.model.Resource

class Quarry(level: Int, rps: Long) : ProducingBuilding(level, rps) {
    override fun getType(): Building.Type = Building.Type.QUARRY
    override fun getResourceType(): Resource.Type = Resource.Type.STONE
}