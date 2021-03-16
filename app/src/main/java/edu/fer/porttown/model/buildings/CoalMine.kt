package edu.fer.porttown.model.buildings

import edu.fer.porttown.model.Building
import edu.fer.porttown.model.Resource

class CoalMine(level: Int, rps: Long) : ProducingBuilding(level, rps) {
    override fun getType(): Building.Type = Building.Type.COAL_MINE
    override fun getResourceType(): Resource.Type = Resource.Type.COAL
}